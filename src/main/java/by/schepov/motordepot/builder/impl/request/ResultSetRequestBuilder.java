package by.schepov.motordepot.builder.impl.request;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.specification.Column;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetRequestBuilder implements Builder<Request> {

    private final RequestBuilder requestBuilder = new RequestBuilder();
    private final ResultSet resultSet;

    public ResultSetRequestBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public void reset() {
        requestBuilder.reset();
    }

    @Override
    public Request build() {
        return requestBuilder.build();
    }

    public ResultSetRequestBuilder withId(Column idColumn) throws SQLException {
        requestBuilder.withId(resultSet.getInt(idColumn.getName()));
        return this;
    }

    public ResultSetRequestBuilder withUser(User user) throws SQLException {
        requestBuilder.withUser(user);
        return this;
    }

    public ResultSetRequestBuilder withRoute(Route route) throws SQLException {
        requestBuilder.withRoute(route);
        return this;
    }

    public ResultSetRequestBuilder withPassengersQuantity(Column passengerQuantityColumn) throws SQLException {
        requestBuilder.withPassengerQuantity(resultSet.getInt(passengerQuantityColumn.getName()));
        return this;
    }

    public ResultSetRequestBuilder withLoad(Column loadColumn) throws SQLException {
        requestBuilder.withLoad(resultSet.getInt(loadColumn.getName()));
        return this;
    }

}
