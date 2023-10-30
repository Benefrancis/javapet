package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.pessoa.PF;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class PFRepository implements Repository<PF, Long> {

    private ConnectionFactory factory;

    private static final AtomicReference<PFRepository> instance = new AtomicReference<>();

    private PFRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static PFRepository build() {
        instance.compareAndSet(null, new PFRepository());
        return instance.get();
    }

    @Override
    public List<PF> findAll() {
        List<PF> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT * FROM TB_PF";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong("ID_PESSOA");
                    String nome = rs.getString("NM_PESSOA");
                    LocalDate nascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                    String tipo = rs.getString("TP_PESSOA");
                    String cpf = rs.getString("NR_CPF");
                    list.add(new PF(id, nome, nascimento, cpf));
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }


    @Override
    public PF findById(Long id) {
        PF pessoa = null;
        var sql = "SELECT * FROM TB_PF where ID_PESSOA = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString("NM_PESSOA");
                    LocalDate nascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                    String cpf = rs.getString("NR_CPF");
                    pessoa = new PF(id, nome, nascimento, cpf);
                }
            } else {
                System.out.println("Dados não encontrados com o id: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, con);
        }
        return pessoa;
    }


    @Override
    public PF persiste(PF pf) {

        var sql = "INSERT INTO TB_PF (ID_PESSOA, NM_PESSOA , DT_NASCIMENTO, TP_PESSOA, NR_CPF) VALUES (0, ?,?,?,?)";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // seta os valores dos parâmetros
            ps.setString(1, pf.getNome());
            ps.setDate(2, Date.valueOf(pf.getNascimento()));
            ps.setString(3, pf.getTipo());
            ps.setString(4, pf.getCPF());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                pf.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possível inserir os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(null, ps, con);
        }
        return pf;
    }

}
