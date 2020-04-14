package by.schepov.motordepot.specification.impl.order;

import by.schepov.motordepot.builder.impl.car.ResultSetCarBuilder;
import by.schepov.motordepot.builder.impl.carname.ResultSetCarNameBuilder;
import by.schepov.motordepot.builder.impl.order.OrderBuilder;
import by.schepov.motordepot.builder.impl.route.ResultSetRouteBuilder;
import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.*;
import by.schepov.motordepot.exception.pool.ConnectionPoolException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.pool.ProxyConnection;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindOrdersByUserIdSpecification implements Specification<Order> {

    private static final Logger LOGGER = LogManager.getLogger(GetAllOrdersSpecification.class);
    private static final String QUERY =
            "SELECT users.id id, user_id, route_id, car_id, orders.driver_id driver_id," +
                    " users.login user_login, users.password user_password, users.role_id user_role_id," +
                    " users.email user_email, users.is_blocked user_blocked," +
                    " drivers.login driver_login, drivers.password driver_password, drivers.role_id driver_role_id," +
                    " drivers.email driver_email, drivers.is_blocked driver_blocked, " +
                    "user_roles.role user_role, driver_roles.role driver_role, " +
                    "route_id, departure_time, departure_location, arrival_time, arrival_location, " +
                    " registration_number, cars.name_id car_name_id, load_capacity, passenger_capacity, status_id, " +
                    " car_mds.name model_name, car_brds.name brand_name, " +
                    "cs.status car_status " +
                    "FROM motor_depot.orders as orders " +
                    "LEFT JOIN motor_depot.users as users on user_id = users.id " +
                    "LEFT JOIN motor_depot.users as drivers on driver_id = drivers.id " +
                    "LEFT JOIN motor_depot.roles as user_roles on user_roles.id = users.role_id " +
                    "LEFT JOIN motor_depot.roles as driver_roles on driver_roles.id = drivers.role_id " +
                    "LEFT JOIN motor_depot.routes as routes on route_id = routes.id " +
                    "LEFT JOIN motor_depot.cars as cars on car_id = cars.id " +
                    "LEFT JOIN motor_depot.car_statuses as cs on cars.status_id = cs.id " +
                    "LEFT JOIN motor_depot.car_names cn on cars.name_id = cn.id " +
                    "LEFT JOIN motor_depot.car_models car_mds on cn.model_id = car_mds.id " +
                    "LEFT JOIN motor_depot.car_brands car_brds on cn.brand_id = car_brds.id " +
                    "WHERE user_id = ?";

    private final ConnectionPool pool = ConnectionPool.INSTANCE;
    private final OrderBuilder orderBuilder = new OrderBuilder();
    private int id;

    public FindOrdersByUserIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public Set<Order> execute() throws SpecificationException {
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetUserBuilder userBuilder = new ResultSetUserBuilder(resultSet);
            ResultSetRouteBuilder routeBuilder = new ResultSetRouteBuilder(resultSet);
            ResultSetCarBuilder carBuilder = new ResultSetCarBuilder(resultSet);
            ResultSetCarNameBuilder carNameBuilder = new ResultSetCarNameBuilder(resultSet);
            LinkedHashSet<Order> orders = new LinkedHashSet<>();
            while (resultSet.next()) {
                orderBuilder.reset();
                userBuilder.reset();
                routeBuilder.reset();
                carBuilder.reset();
                carNameBuilder.reset();
                User user = userBuilder.withId(Column.USER_ID).withEmail(Column.USER_EMAIL).withLogin(Column.USER_LOGIN)
                        .withPassword(Column.USER_PASSWORD).withRole(Column.USER_ROLE).withBlocked(Column.USER_BLOCKED)
                        .build();
                userBuilder.reset();
                User driver = userBuilder.withId(Column.DRIVER_ID).withEmail(Column.DRIVER_EMAIL).withLogin(Column.DRIVER_LOGIN)
                        .withPassword(Column.DRIVER_PASSWORD).withRole(Column.DRIVER_ROLE).withBlocked(Column.DRIVER_BLOCKED)
                        .build();
                Route route = routeBuilder.withId(Column.ROUTE_ID).withDepartureTime(Column.DEPARTURE_TIME)
                        .withDepartureLocation(Column.DEPARTURE_LOCATION).withArrivalTime(Column.ARRIVAL_TIME)
                        .withArrivalLocation(Column.ARRIVAL_LOCATION).build();
                CarName carName = carNameBuilder.withCarBrand(Column.BRAND_NAME).withCarModel(Column.MODEL_NAME)
                        .withId(Column.CAR_NAME_ID).build();
                Car car = carBuilder.withId(Column.CAR_ID).withDriver(driver).withLoadCapacity(Column.LOAD_CAPACITY)
                        .withPassengerCapacity(Column.PASSENGER_CAPACITY).withCarName(carName).withRegistrationNumber(Column.REGISTRATION_NUMBER)
                        .withCarStatus(Column.CAR_STATUS).build();
                Order order = orderBuilder.withId(resultSet.getInt(Column.ID.getName())).withRoute(route)
                        .withDriver(driver).withUser(user).withCar(car).build();
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.warn(e);
            throw new SpecificationException(e);
        }
    }

}
