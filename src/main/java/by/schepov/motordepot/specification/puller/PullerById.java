package by.schepov.motordepot.specification.puller;

import java.sql.Connection;
import java.sql.SQLException;

public interface PullerById<T> {
    T pull(int id, Connection connection) throws SQLException;
}
