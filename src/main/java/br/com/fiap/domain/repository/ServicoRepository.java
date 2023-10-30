package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.entity.servico.*;
import br.com.fiap.domain.service.AnimalService;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

public class ServicoRepository implements Repository<Servico, Long> {


    AnimalService animalService = new AnimalService();

    private ConnectionFactory factory;

    private static final AtomicReference<ServicoRepository> instance = new AtomicReference<>();

    private ServicoRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static ServicoRepository build() {
        instance.compareAndSet(null, new ServicoRepository());
        return instance.get();
    }


    @Override
    public List<Servico> findAll() {

        List<Servico> servicos = new Vector<>();

        var sql = "SELECT * FROM TB_SERVICO";

        Connection conn = factory.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long idServico = rs.getLong("ID_SERVICO");
                    String tipo = rs.getString("TP_SERVICO");
                    String descricao = rs.getString("DS_SERVICO");
                    LocalDate realizacao = rs.getDate("DT_REALIZACAO").toLocalDate();
                    Long idAnimal = rs.getLong("ANIMAL");
                    Animal animal = animalService.findById(idAnimal);
                    Servico servico = null;
                    if (tipo.equalsIgnoreCase("Banho")) {
                        servico = new Banho();
                    }
                    if (tipo.equalsIgnoreCase("Consulta")) {
                        servico = new Consulta();
                    }
                    if (tipo.equalsIgnoreCase("Tosa")) {
                        servico = new Tosa();
                    }
                    if (tipo.equalsIgnoreCase("Vacina")) {
                        servico = new Vacina();
                    }
                    servico.setAnimal(animal);
                    servico.setRealizacao(realizacao);
                    servico.setDescricao(descricao);
                    servico.setId(idServico);
                    servico.setTipo(tipo);
                    servicos.add(servico);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar o Serviço: " + e.getMessage());
        } finally {
            fecharObjetos(rs, st, conn);
        }
        return servicos;
    }

    @Override
    public Servico findById(Long id) {

        Servico servico = null;

        var sql = "SELECT * FROM TB_SERVICO WHERE ID_SERVICO = ?";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    Long idServico = rs.getLong("ID_SERVICO");
                    String tipo = rs.getString("TP_SERVICO");
                    String descricao = rs.getString("DS_SERVICO");
                    LocalDate realizacao = rs.getDate("DT_REALIZACAO").toLocalDate();
                    Long idAnimal = rs.getLong("ANIMAL");
                    Animal animal = animalService.findById(idAnimal);

                    if (tipo.equalsIgnoreCase("Banho")) {
                        servico = new Banho();
                    }
                    if (tipo.equalsIgnoreCase("Consulta")) {
                        servico = new Consulta();
                    }
                    if (tipo.equalsIgnoreCase("Tosa")) {
                        servico = new Tosa();
                    }
                    if (tipo.equalsIgnoreCase("Vacina")) {
                        servico = new Vacina();
                    }
                    servico.setAnimal(animal);
                    servico.setRealizacao(realizacao);
                    servico.setDescricao(descricao);
                    servico.setId(idServico);
                    servico.setTipo(tipo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar o Servico: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return servico;
    }

    @Override
    public Servico persiste(Servico s) {

        s.setId(0L);

        var sql = "INSERT INTO TB_SERVICO (TP_SERVICO, DS_SERVICO, DT_REALIZACAO, ANIMAL) " +
                " values (?,?,?,?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;

        try {

            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s.getTipo());
            ps.setString(2, s.getDescricao());
            ps.setDate(3, Date.valueOf(s.getRealizacao()));
            ps.setLong(4, s.getAnimal().getId());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                s.setId(id);
            }

            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar o servico no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }
        return s;
    }

}
