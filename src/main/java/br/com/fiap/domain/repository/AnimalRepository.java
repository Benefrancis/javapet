package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.entity.animal.Cachorro;
import br.com.fiap.domain.entity.animal.Gato;
import br.com.fiap.domain.entity.pessoa.Pessoa;
import br.com.fiap.domain.service.PFService;
import br.com.fiap.domain.service.PJService;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        instance.compareAndSet( null, new AnimalRepository() );
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

            rs = st.executeQuery( sql );

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong( "ID_ANIMAL" );
                    String nome = rs.getString( "NM_ANIMAL" );
                    String raca = rs.getString( "RACA" );
                    String descricao = rs.getString( "DS_ANIMAL" );
                    String tipo = rs.getString( "TP_ANIMAL" );

                    Long idDono = rs.getLong( "DONO" );

                    Pessoa dono = null;

                    dono = pfService.findById( idDono );

                    if (Objects.isNull( dono )) {
                        dono = pjService.findById( idDono );
                    }

                    if (tipo.equals( "CACHORRO" )) {
                        list.add( new Cachorro( id, nome, raca, descricao, dono ) );
                    } else if (tipo.equals( "GATO" )) {
                        list.add( new Gato( id, nome, raca, descricao, dono ) );
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar o Animal" );
        } finally {
            fecharObjetos( rs, st, con );
        }
        return list;
    }

    @Override
    public Animal findById(Long id) {
        return null;
    }

    @Override
    public Animal persiste(Animal animal) {
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
