package by.schepov.motordepot.builder.impl.route;


import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.specification.Column;

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

    public ResultSetRouteBuilder withId(Column idColumn) throws SQLException {
        routeBuilder.withId(resultSet.getInt(idColumn.getName()));
        return this;
    }

    public ResultSetRouteBuilder withDepartureLocation(Column departureLocationColumn) throws SQLException {
        routeBuilder.withDepartureLocation(resultSet.getString(departureLocationColumn.getName()));
        return this;
    }

    public ResultSetRouteBuilder withDepartureTime(Column departureTimeColumn) throws SQLException {
        routeBuilder.withDepartureTime(resultSet.getDate(departureTimeColumn.getName()));
        return this;
    }

    public ResultSetRouteBuilder withArrivalLocation(Column arrivalLocationColumn) throws SQLException {
        routeBuilder.withArrivalLocation(resultSet.getString(arrivalLocationColumn.getName()));
        return this;
    }

    public ResultSetRouteBuilder withArrivalTime(Column arrivalTimeColumn) throws SQLException {
        routeBuilder.withArrivalTime(resultSet.getDate(arrivalTimeColumn.getName()));
        return this;
    }
}
