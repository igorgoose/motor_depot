package by.schepov.motordepot.specification.query.impl.car;

import by.schepov.motordepot.builder.impl.car.ResultSetCarBuilder;
import by.schepov.motordepot.builder.impl.carname.ResultSetCarNameBuilder;
import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarName;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.query.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindCarByIdQuerySpecification implements QuerySpecification<Car> {

    private int id;
    private static final Logger LOGGER = LogManager.getLogger(GetAllCarsQuerySpecification.class);
    private static final String QUERY = "SELECT cars.id, driver_id, registration_number, name_id, load_capacity, " +
            "passenger_capacity, " +
            "st.status car_status, brand_id, model_id, brands.name brand_name, models.name model_name, " +
            "login, password, ust.status user_status, role_id, email, role " +
            "FROM motor_depot.cars as cars " +
            "LEFT JOIN motor_depot.users as drivers on driver_id = drivers.id " +
            "LEFT JOIN motor_depot.roles as roles on roles.id = role_id " +
            "LEFT JOIN motor_depot.user_statuses ust on ust.id = drivers.status_id " +
            "LEFT JOIN motor_depot.car_statuses as st on cars.status_id = st.id " +
            "LEFT JOIN motor_depot.car_names as names on name_id = names.id " +
            "LEFT JOIN motor_depot.car_brands as brands on brand_id = brands.id " +
            "LEFT JOIN motor_depot.car_models as models on model_id = models.id " +
            "WHERE cars.id = ?";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    public FindCarByIdQuerySpecification(int id){
        this.id = id;
    }

    @Override
    public Set<Car> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetCarBuilder carBuilder = new ResultSetCarBuilder(resultSet);
            ResultSetUserBuilder driverBuilder = new ResultSetUserBuilder(resultSet);
            ResultSetCarNameBuilder carNameBuilder = new ResultSetCarNameBuilder(resultSet);
            LinkedHashSet<Car> cars = new LinkedHashSet<>();
            while (resultSet.next()) {
                carBuilder.reset();
                driverBuilder.reset();
                carNameBuilder.reset();
                User driver = driverBuilder.withId(Column.DRIVER_ID).withEmail(Column.EMAIL).withLogin(Column.LOGIN)
                        .withPassword(Column.PASSWORD).withRole(Column.ROLE).withStatus(Column.USER_STATUS)
                        .build();
                CarName carName = carNameBuilder.withId(Column.NAME_ID).withCarModel(Column.MODEL_NAME)
                        .withCarBrand(Column.BRAND_NAME).build();
                Car car = carBuilder.withId(Column.ID).withRegistrationNumber(Column.REGISTRATION_NUMBER).withCarName(carName)
                        .withPassengerCapacity(Column.PASSENGER_CAPACITY).withLoadCapacity(Column.LOAD_CAPACITY).withDriver(driver)
                        .withCarStatus(Column.CAR_STATUS).build();
                cars.add(car);
            }
            return cars;
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
