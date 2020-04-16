package by.schepov.motordepot.specification.impl.car;

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
import by.schepov.motordepot.specification.Specification;
import by.schepov.motordepot.specification.impl.user.GetAllUsersSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindFreeCarSpecification implements Specification<Car> {

    private int loadCapacityRequired;
    private int passengerCapacityRequired;
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(FindFreeCarSpecification.class);

    public static final String QUERY = "SELECT users.id id, user_id, departure_location, arrival_location, car_id, orders.driver_id driver_id," +
            " users.login user_login, users.password user_password, users.role_id user_role_id," +
            " users.email user_email, users.is_blocked user_blocked," +
            " drivers.login driver_login, drivers.password driver_password, drivers.role_id driver_role_id," +
            " drivers.email driver_email, drivers.is_blocked driver_blocked, " +
            "user_roles.role user_role, driver_roles.role driver_role, " +
            " registration_number, cars.name_id car_name_id, load_capacity, passenger_capacity, status_id, " +
            " car_mds.name model_name, car_brds.name brand_name, " +
            "cs.status car_status " +
            "FROM motor_depot.orders as orders " +
            "LEFT JOIN motor_depot.users as users on user_id = users.id " +
            "LEFT JOIN motor_depot.users as drivers on driver_id = drivers.id " +
            "LEFT JOIN motor_depot.roles as user_roles on user_roles.id = users.role_id " +
            "LEFT JOIN motor_depot.roles as driver_roles on driver_roles.id = drivers.role_id " +
            "LEFT JOIN motor_depot.cars as cars on car_id = cars.id " +
            "LEFT JOIN motor_depot.car_statuses as cs on cars.status_id = cs.id " +
            "LEFT JOIN motor_depot.car_names cn on cars.name_id = cn.id " +
            "LEFT JOIN motor_depot.car_models car_mds on cn.model_id = car_mds.id " +
            "LEFT JOIN motor_depot.car_brands car_brds on cn.brand_id = car_brds.id";


    public FindFreeCarSpecification(int loadCapacityRequired, int passengerCapacityRequired){
        this.loadCapacityRequired = loadCapacityRequired;
        this.passengerCapacityRequired = passengerCapacityRequired;
    }

    @Override
    public Set<Car> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
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
                        .withPassword(Column.PASSWORD).withRole(Column.ROLE).withBlocked(Column.IS_BLOCKED)
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
