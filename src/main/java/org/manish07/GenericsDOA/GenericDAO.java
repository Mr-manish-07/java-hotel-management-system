package org.manish07.GenericsDOA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// This is used in doa classes inside theri implemenation with data so similir code no need to write again and again

public class GenericDAO {

    private final Connection connection;

    public GenericDAO(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(String sql, PreparedStatementSetter setter, RowMapper<T> mapper) {

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            setter.setValues(ps);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? mapper.map(rs) : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
