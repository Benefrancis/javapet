package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.pessoa.PJ;
import br.com.fiap.domain.entity.pessoa.Pessoa;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class PJRepository implements Repository<PJ, Long> {

    private ConnectionFactory factory;

    private static final AtomicReference<PJRepository> instance = new AtomicReference<>();

    private PJRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static PJRepository build() {
        instance.compareAndSet( null, new PJRepository() );
        return instance.get();
    }

    @Override
    public List<PJ> findAll() {
        List<PJ> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        var sql = "SELECT * FROM TB_PJ";
        try {
            st = con.createStatement();
            rs = st.executeQuery( sql );

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong( "ID_PESSOA" );
                    String nome = rs.getString( "NM_PESSOA" );
                    LocalDate nascimento = rs.getDate( "DT_NASCIMENTO" ).toLocalDate();
                    String tipo = rs.getString( "TP_PESSOA" );
                    String cnpj = rs.getString( "NR_CNPJ" );
                    list.add( new PJ( id, nome, nascimento, cnpj ) );
                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, st, con );
        }
        return list;
    }

    @Override
    public PJ findById(Long id) {

        PJ pessoa = null;

        Connection con = factory.getConnection();

        ResultSet rs = null;

        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_PJ where ID_PESSOA=?";

        try {
            ps = con.prepareStatement( sql );

            ps.setLong( 1, id );

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString( "NM_PESSOA" );
                    LocalDate nascimento = rs.getDate( "DT_NASCIMENTO" ).toLocalDate();
                    String cnpj = rs.getString( "NR_CNPJ" );
                    pessoa = new PJ( id, nome, nascimento, cnpj );
                }
            }
        } catch (SQLException e) {
            System.out.println( "Dados não encontrados com o id: " + id );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return pessoa;
    }

    @Override
    public PJ persiste(PJ pj) {
        var sql = "INSERT INTO TB_PJ ( ID_PESSOA , NM_PESSOA, DT_NASCIMENTO, TP_PESSOA, NR_CNPJ) values (0, ?,?,?,?)";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString( 1, pj.getNome() );
            ps.setDate( 2, Date.valueOf( pj.getNascimento() ) );
            ps.setString( 3, pj.getTipo() );
            ps.setString( 4, pj.getCNPJ() );
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                pj.setId(id);
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return pj;
    }


}
