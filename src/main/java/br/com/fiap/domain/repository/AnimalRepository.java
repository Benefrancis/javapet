package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.entity.animal.Cachorro;
import br.com.fiap.domain.entity.animal.Gato;
import br.com.fiap.domain.entity.pessoa.PF;
import br.com.fiap.domain.entity.pessoa.Pessoa;
import br.com.fiap.domain.service.PFService;
import br.com.fiap.domain.service.PJService;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class AnimalRepository implements Repository<Animal, Long> {

    PFService pfService = new PFService();

    PJService pjService = new PJService();

    private ConnectionFactory factory;

    private static final AtomicReference<AnimalRepository> instance = new AtomicReference<>();

    private AnimalRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static AnimalRepository build() {
        instance.compareAndSet(null, new AnimalRepository());
        return instance.get();
    }

    @Override
    public List<Animal> findAll() {


        List<Animal> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM TB_ANIMAL";

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_ANIMAL");
                    String nome = rs.getString("NM_ANIMAL");
                    String raca = rs.getString("RACA");
                    String descricao = rs.getString("DS_ANIMAL");
                    String tipo = rs.getString("TP_ANIMAL");
                    Long idDono = rs.getLong("DONO");
                    Pessoa dono = null;
                    dono = pfService.findById(idDono);
                    if (Objects.isNull(dono)) {
                        dono = pjService.findById(idDono);
                    }
                    if (tipo.equals("CACHORRO")) {
                        list.add(new Cachorro(id, nome, raca, descricao, dono));
                    } else if (tipo.equals("GATO")) {
                        list.add(new Gato(id, nome, raca, descricao, dono));
                    }

                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar o Animal");
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Animal findById(Long id) {
        Animal animal = null;
        var sql = "SELECT * FROM TB_ANIMAL where ID_ANIMAL=?";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    String nome = rs.getString("NM_ANIMAL");
                    String raca = rs.getString("RACA");
                    String tipo = rs.getString("TP_ANIMAL");
                    String descricao = rs.getString("DS_ANIMAL");
                    Long dono = rs.getLong("DONO");

                    Pessoa pessoa = pfService.findById(dono);

                    if (Objects.isNull(pessoa)) {
                        pessoa = pjService.findById(dono);
                    }

                    if (Objects.isNull(pessoa)) return null;

                    if (tipo.equals("CACHORRO")) {
                        animal = new Cachorro(id, nome, raca, descricao, pessoa);
                    } else if (tipo.equals("GATO")) {
                        animal = new Gato(id, nome, raca, descricao, pessoa);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return animal;
    }

    @Override
    public Animal persiste(Animal animal) {

        var sql = "INSERT INTO TB_ANIMAL " +
                " (ID_ANIMAL, NM_ANIMAL, DS_ANIMAL, TP_ANIMAL, DONO, RACA) " +
                " values (0, ?, ?, ?, ?, ?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getDescricao());
            ps.setString(3, animal.getTipo());
            ps.setLong(4, animal.getDono().getId());
            ps.setString(5, animal.getRaca());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                animal.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar o animal no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }
        return animal;
    }


}
