package by.schepov.motordepot.specification.puller.impl;

import by.schepov.motordepot.builder.impl.car.ResultSetCarBuilder;
import by.schepov.motordepot.builder.impl.carname.ResultSetCarNameBuilder;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarName;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.puller.PullerById;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum CarPullerById implements PullerById<Car> {

    INSTANCE;

    private static final String QUERY = "SELECT cars.id, driver_id, registration_number, name_id, load_capacity, " +
            "passenger_capacity, status_id, " +
            "st.status status, brand_id, model_id, brands.name brand, models.name model " +
            "FROM motor_depot.cars as cars " +
            "LEFT JOIN motor_depot.car_statuses as st on status_id = st.id " +
            "LEFT JOIN motor_depot.car_names as names on name_id = names.id " +
            "LEFT JOIN motor_depot.car_brands as brands on brand_id = brands.id " +
            "LEFT JOIN motor_depot.car_models as models on model_id = models.id " +
            "WHERE cars.id = ?";
    private final UserPullerById userPuller = UserPullerById.INSTANCE;

    @Override
    public Car pull(int id, Connection connection) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ResultSetCarNameBuilder carNameBuilder = new ResultSetCarNameBuilder(resultSet);
                    carNameBuilder.reset();
                    CarName carName = carNameBuilder.withId(Column.NAME_ID).withCarBrand(Column.BRAND)
                            .withCarModel(Column.MODEL).build();

                    User driver = userPuller.pull(resultSet.getInt(Column.DRIVER_ID.getName()), connection);
                    ResultSetCarBuilder resultSetCarBuilder = new ResultSetCarBuilder(resultSet);
                    resultSetCarBuilder.reset();
                    return resultSetCarBuilder.withId(Column.ID).withLoadCapacity(Column.LOAD_CAPACITY)
                            .withPassengerCapacity(Column.PASSENGER_CAPACITY).withCarName(carName)
                            .withDriver(driver).withRegistrationNumber(Column.REGISTRATION_NUMBER)
                            .withCarStatus(CarStatus.valueOf(resultSet.getString(Column.STATUS.getName()))).build();
                }
                return null;
            }
        }
    }

}
