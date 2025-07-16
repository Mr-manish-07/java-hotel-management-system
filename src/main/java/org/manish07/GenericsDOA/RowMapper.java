package org.manish07.GenericsDOA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@FunctionalInterface
public interface RowMapper<T> {
    T map(ResultSet rs) throws SQLException;
}


