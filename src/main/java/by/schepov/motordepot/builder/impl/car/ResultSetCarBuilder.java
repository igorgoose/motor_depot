package by.schepov.motordepot.builder.impl.car;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.*;
import by.schepov.motordepot.specification.Column;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetCarBuilder implements Builder<Car> {

    private final CarBuilder carBuilder = new CarBuilder();
    private final ResultSet resultSet;

    public ResultSetCarBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public void reset() {
        carBuilder.reset();
    }

    @Override
    public Car build() {
        return carBuilder.build();
    }

    public ResultSetCarBuilder withId(Column idColumn) throws SQLException {
        carBuilder.withId(resultSet.getInt(idColumn.getName()));
        return this;
    }

    public ResultSetCarBuilder withDriver(User driver){
        carBuilder.withDriver(driver);
        return this;
    }

    public ResultSetCarBuilder withRegistrationNumber(Column registrationNumberColumn) throws SQLException {
        carBuilder.withRegistrationNumber(resultSet.getString(registrationNumberColumn.getName()));
        return this;
    }

    public ResultSetCarBuilder withCarName(CarName carName){
        carBuilder.withCarName(carName);
        return this;
    }

    public ResultSetCarBuilder withLoadCapacity(Column loadCapacityColumn) throws SQLException {
        carBuilder.withLoadCapacity(resultSet.getInt(loadCapacityColumn.getName()));
        return this;
    }

    public ResultSetCarBuilder withPassengerCapacity(Column passengerCapacityColumn) throws SQLException {
        carBuilder.withPassengerCapacity(resultSet.getInt(passengerCapacityColumn.getName()));
        return this;
    }

    public ResultSetCarBuilder withCarStatus(Column carStatusColumn) throws SQLException {
        carBuilder.withCarStatus(CarStatus.valueOf(resultSet.getString(carStatusColumn.getName())));
        return this;
    }
}
