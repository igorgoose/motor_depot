package by.schepov.motordepot.builder.impl.order;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.builder.impl.request.ResultSetRequestBuilder;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.specification.Column;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetOrderBuilder implements Builder<Order> {

    private final OrderBuilder orderBuilder = new OrderBuilder();
    private final ResultSet resultSet;

    public ResultSetOrderBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public void reset() {
        orderBuilder.reset();
    }

    @Override
    public Order build() {
        return orderBuilder.build();
    }

    public ResultSetOrderBuilder withId(Column idColumn) throws SQLException {
        orderBuilder.withId(resultSet.getInt(idColumn.getName()));
        return this;
    }

    public ResultSetOrderBuilder withUser(User user) throws SQLException {
        orderBuilder.withUser(user);
        return this;
    }

    public ResultSetOrderBuilder withDriver(User driver){
        orderBuilder.withDriver(driver);
        return this;
    }

    public ResultSetOrderBuilder withCar(Car car) {
        orderBuilder.withCar(car);
        return this;
    }

    public ResultSetOrderBuilder withDepartureLocation(Column departureLocationColumn) throws SQLException {
        orderBuilder.withDepartureLocation(resultSet.getString(departureLocationColumn.getName()));
        return this;
    }

    public ResultSetOrderBuilder withArrivalLocation(Column arrivalLocationColumn) throws SQLException {
        orderBuilder.withArrivalLocation(resultSet.getString(arrivalLocationColumn.getName()));
        return this;
    }

}
