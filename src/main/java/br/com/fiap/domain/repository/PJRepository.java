package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.pessoa.PJ;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        return null;
    }

    @Override
    public PJ persiste(PJ pj) {
        return null;
    }

    private static void fecharObjetos(ResultSet rs, Statement st, Connection con) {
        try {
            if (Objects.nonNull( rs ) && !rs.isClosed()) {
                rs.close();
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            System.err.println( "Erro ao encerrar o ResultSet, a Connection e o Statment!\n" + e.getMessage() );
        }
    }
}
