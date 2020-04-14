package by.schepov.motordepot.specification.puller.impl;

import by.schepov.motordepot.builder.impl.route.ResultSetRouteBuilder;
import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.puller.PullerById;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum RoutePullerById implements PullerById<Route> {

    INSTANCE;

    private static final String QUERY = "SELECT * FROM routes WHERE id = ?";

    @Override
    public Route pull(int id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetRouteBuilder builder = new ResultSetRouteBuilder(resultSet);
                if (resultSet.next()) {
                    builder.reset();
                    return builder.withDepartureLocation(Column.DEPARTURE_LOCATION)
                            .withArrivalLocation(Column.ARRIVAL_LOCATION)
                            .withDepartureTime(Column.DEPARTURE_TIME)
                            .withArrivalTime(Column.ARRIVAL_TIME)
                            .build();
                }
                return null;
            }
        }
    }

}
