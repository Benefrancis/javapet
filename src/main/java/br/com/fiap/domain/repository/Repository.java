package br.com.fiap.domain.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public interface Repository<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public T persiste(T t);


    default void fecharObjetos(ResultSet rs, Statement st, Connection con) {
        try {
            if (Objects.nonNull(rs) && !rs.isClosed()) rs.close();
            if (Objects.nonNull(st) && !st.isClosed()) st.close();
            if (Objects.nonNull(con) && !con.isClosed()) con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao encerrar o ResultSet, a Connection e o Statment!\n" + e.getMessage());
        }
    }

}
