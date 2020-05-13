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

public class FindFreeCarsQuerySpecification implements QuerySpecification<Car> {

    private int loadCapacityRequired;
    private int passengerCapacityRequired;
    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(FindFreeCarsQuerySpecification.class);

    public static final String QUERY = "select c.id id, c.driver_id driver_id, registration_number, name_id, " +
            "load_capacity, passenger_capacity, cb.name brand_name, cm.name model_name, " +
            "login, password, email, role, ust.status user_status, " +
            "cs.status car_status " +
            "from motor_depot.orders o " +
            "right join motor_depot.cars c on c.id = o.car_id " +
            "left join motor_depot.users dr on dr.id = c.driver_id " +
            "left join motor_depot.roles r on dr.role_id = r.id " +
            "LEFT JOIN motor_depot.user_statuses ust on ust.id = dr.status_id " +
            "left join motor_depot.car_names cn on cn.id = c.name_id " +
            "left join motor_depot.car_models cm on cn.model_id = cm.id " +
            "left join  motor_depot.car_brands cb on cb.id = cn.brand_id " +
            "left join motor_depot.car_statuses cs on cs.id = c.status_id " +
            "WHERE dr.status_id = 1 and c.status_id = 1 and load_capacity >= ? and passenger_capacity >= ? " +
            "group by id " +
            "having count(*) = sum(is_complete) or sum(is_complete) is null " +
            "order by sum(is_complete)";


    public FindFreeCarsQuerySpecification(int loadCapacityRequired, int passengerCapacityRequired) {
        this.loadCapacityRequired = loadCapacityRequired;
        this.passengerCapacityRequired = passengerCapacityRequired;
    }

    @Override
    public Set<Car> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, loadCapacityRequired);
            preparedStatement.setInt(2, passengerCapacityRequired);
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
