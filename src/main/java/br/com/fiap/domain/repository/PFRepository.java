package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.pessoa.PF;
import br.com.fiap.infra.ConnectionFactory;
import oracle.jdbc.OracleDatabaseMetaData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class PFRepository implements Repository<PF, Long> {


    private static final AtomicReference<PFRepository> instance = new AtomicReference<>();

    private ConnectionFactory factory;


    private PFRepository(){
        this.factory = ConnectionFactory.build();
    }

    public static PFRepository build(){
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
            st = con.createStatement();
            String sql = "SELECT * FROM TB_PF";

            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()){
                while (rs.next()){
                    Long id = rs.getLong("ID_PESSOA");
                    String nome = rs.getString("NM_PESSOA");
                    LocalDate nascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                    String tipo = rs.getString("TP_PESSOA");
                    String cpf = rs.getString("NR_CPF");

                    list.add(new PF(id, nome, nascimento, cpf));
                }
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel consultar os dados! \n" +e.getMessage());
        } finally {
           fecharObjetos(rs, st,con);
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

            rs =  ps.executeQuery();

            if (rs.isBeforeFirst()){
                while(rs.next()){

                    String nome = rs.getString("NM_PESSOA");
                    LocalDate nascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                    String cpf = rs.getString("NR_CPF");

                    pessoa = new PF(id, nome, nascimento, cpf);
                }
            }else {
                System.out.println("Dados não enconstrados com o id : "+ id);
            }


        }catch (SQLException e){
            System.err.println("Não foi possivel consultar os dados! \n" +e.getMessage());
        }finally {
            fecharObjetos(rs, ps,con);
        }
        return pessoa;
    }

    @Override
    public List<PF> findByTexto(String texto) {
        return null;
    }

    @Override
    public PF persiste(PF pf) {
        var sql = "BEGIN INSERT INTO TB_PF(NM_PESSOA, DT_NASCIMENTO, TP_PESSOA, NR_CPF) VALUES (?, ?, ?, ?) returning ID_PESSOA into ?; END;";

        Connection con = factory.getConnection();
        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
                cs.setString(1, pf.getNome());
                cs.setDate(2, Date.valueOf(pf.getNascimento()));
                cs.setString(3, pf.getTipo());
                cs.setString(4, pf.getCPF());

                cs.registerOutParameter(5, Types.BIGINT);

                cs.executeUpdate();
                pf.setId(cs.getLong(5));

        }catch (SQLException e){
            System.err.println("Não foi possivel consultar os dados! \n" +e.getMessage());
        }finally {
            fecharObjetos(null, cs, con);
        }

         return pf;
    }

    @Override
    public PF update(PF pf) {
        return null;
    }

    @Override
    public boolean delete(PF pf) {
        return false;
    }

    private static void fecharObjetos(ResultSet rs, Statement st , Connection con) {
        try {


            if (Objects.nonNull(rs) && ! rs.isClosed()){
                rs.close();
                boolean rsClosed = rs.isClosed();
                st.close();
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao encerrar o ResultSet! \n" +e.getMessage());
        }
    }
}

