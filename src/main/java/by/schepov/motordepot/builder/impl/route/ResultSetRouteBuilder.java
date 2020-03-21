package by.schepov.motordepot.builder.impl.route;


import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetRouteBuilder implements Builder<Route> {

    private final RouteBuilder routeBuilder = new RouteBuilder();
    private final ResultSet resultSet;

    public ResultSetRouteBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public void reset() {
        routeBuilder.reset();
    }

    @Override
    public Route build() {
        return routeBuilder.build();
    }

    public ResultSetRouteBuilder withDepartureLocation(String departureLocationColumnName) throws SQLException {
        routeBuilder.withDepartureLocation(resultSet.getString(departureLocationColumnName));
        return this;
    }

    public ResultSetRouteBuilder withDepartureTime(String departureTimeColumnName) throws SQLException {
        routeBuilder.withDepartureLocation(resultSet.getString(departureTimeColumnName));
        return this;
    }

    public ResultSetRouteBuilder withArrivalLocation(String arrivalLocationColumnName) throws SQLException {
        routeBuilder.withDepartureLocation(resultSet.getString(arrivalLocationColumnName));
        return this;
    }

    public ResultSetRouteBuilder withArrivalTime(String arrivalTimeColumnName) throws SQLException {
        routeBuilder.withDepartureLocation(resultSet.getString(arrivalTimeColumnName));
        return this;
    }
}
