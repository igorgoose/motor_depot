package by.schepov.motordepot.specification.query.impl.order;

import by.schepov.motordepot.builder.impl.car.ResultSetCarBuilder;
import by.schepov.motordepot.builder.impl.carname.ResultSetCarNameBuilder;
import by.schepov.motordepot.builder.impl.order.OrderBuilder;
import by.schepov.motordepot.builder.impl.order.ResultSetOrderBuilder;
import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarName;
import by.schepov.motordepot.entity.Order;
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

public class FindOrderByIdQuerySpecification implements QuerySpecification<Order> {

    private int id;
    private static final Logger LOGGER = LogManager.getLogger(FindOrderByIdQuerySpecification.class);
    private static final String QUERY =
            "SELECT orders.id id, user_id, departure_location, arrival_location, car_id, is_complete, " +
                    " users.login user_login, users.password user_password, users.role_id user_role_id," +
                    " users.email user_email, ust.status user_status," +
                    " drivers.login driver_login, drivers.password driver_password, drivers.role_id driver_role_id," +
                    " drivers.email driver_email,  dst.status driver_status, " +
                    "user_roles.role user_role, driver_roles.role driver_role, " +
                    " registration_number, cars.name_id car_name_id, driver_id, load_capacity, passenger_capacity, " +
                    " car_mds.name model_name, car_brds.name brand_name, " +
                    "cs.status car_status " +
                    "FROM motor_depot.orders orders " +
                    "LEFT JOIN motor_depot.users users on user_id = users.id " +
                    "LEFT JOIN motor_depot.roles user_roles on user_roles.id = users.role_id " +
                    "LEFT JOIN motor_depot.user_statuses ust on ust.id = users.status_id " +
                    "LEFT JOIN motor_depot.cars cars on car_id = cars.id " +
                    "LEFT JOIN motor_depot.users drivers on driver_id = drivers.id " +
                    "LEFT JOIN motor_depot.user_statuses dst on dst.id = drivers.status_id " +
                    "LEFT JOIN motor_depot.roles driver_roles on driver_roles.id = drivers.role_id " +
                    "LEFT JOIN motor_depot.car_statuses cs on cars.status_id = cs.id " +
                    "LEFT JOIN motor_depot.car_names cn on cars.name_id = cn.id " +
                    "LEFT JOIN motor_depot.car_models car_mds on cn.model_id = car_mds.id " +
                    "LEFT JOIN motor_depot.car_brands car_brds on cn.brand_id = car_brds.id " +
                    "WHERE orders.id = ?";

    private final ConnectionPool pool = ConnectionPool.INSTANCE;
    private final OrderBuilder orderBuilder = new OrderBuilder();

    public FindOrderByIdQuerySpecification(int id){
        this.id = id;
    }

    @Override
    public Set<Order> execute() throws SpecificationException {
        try(ProxyConnection connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetUserBuilder userBuilder = new ResultSetUserBuilder(resultSet);
            ResultSetCarBuilder carBuilder = new ResultSetCarBuilder(resultSet);
            ResultSetCarNameBuilder carNameBuilder = new ResultSetCarNameBuilder(resultSet);
            ResultSetOrderBuilder orderBuilder = new ResultSetOrderBuilder(resultSet);
            LinkedHashSet<Order> orders = new LinkedHashSet<>();
            while (resultSet.next()) {
                orderBuilder.reset();
                userBuilder.reset();
                carBuilder.reset();
                carNameBuilder.reset();
                User user = userBuilder.withId(Column.USER_ID).withEmail(Column.USER_EMAIL).withLogin(Column.USER_LOGIN)
                        .withPassword(Column.USER_PASSWORD).withRole(Column.USER_ROLE).withStatus(Column.USER_STATUS)
                        .build();
                userBuilder.reset();
                User driver = userBuilder.withId(Column.DRIVER_ID).withEmail(Column.DRIVER_EMAIL).withLogin(Column.DRIVER_LOGIN)
                        .withPassword(Column.DRIVER_PASSWORD).withRole(Column.DRIVER_ROLE).withStatus(Column.DRIVER_STATUS)
                        .build();
                CarName carName = carNameBuilder.withCarBrand(Column.BRAND_NAME).withCarModel(Column.MODEL_NAME)
                        .withId(Column.CAR_NAME_ID).build();
                Car car = carBuilder.withId(Column.CAR_ID).withDriver(driver).withLoadCapacity(Column.LOAD_CAPACITY)
                        .withPassengerCapacity(Column.PASSENGER_CAPACITY).withCarName(carName).withRegistrationNumber(Column.REGISTRATION_NUMBER)
                        .withCarStatus(Column.CAR_STATUS).build();
                Order order = orderBuilder.withId(Column.ID).withDepartureLocation(Column.DEPARTURE_LOCATION).withArrivalLocation(Column.ARRIVAL_LOCATION)
                        .withUser(user).withCar(car).withComplete(Column.IS_COMPLETE).build();
                orders.add(order);
            }
            return orders;
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }
}
