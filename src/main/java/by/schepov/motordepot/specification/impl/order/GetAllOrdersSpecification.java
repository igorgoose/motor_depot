package by.schepov.motordepot.specification.impl.order;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.pool.ConnectionPool;
import by.schepov.motordepot.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class GetAllOrdersSpecification implements Specification<Order> {

    private static final Logger LOGGER = LogManager.getLogger(GetAllOrdersSpecification.class);
    private static final String QUERY =
            "SELECT id, user_id, route_id, car_id, driver_id" +
                    ", login, password, role_id, email, is_blocked, " +
                    "route_id, departure_time, departure_location, arrival_time, arrival_location, role " +
                    "FROM motor_depot.requests as reqs " +
                    "LEFT JOIN motor_depot.users as users on user_id = users.id " +
                    "LEFT JOIN motor_depot.routes as routes on route_id = routes.id " +
                    "LEFT JOIN motor_depot.cars as cars on car_id = cars.id " +
                    "LEFT JOIN motor_depot.users as drivers on driver_id = drivers.id " +
                    "LEFT JOIN motor_depot.roles as user_roles on user_roles.id = users.role_id " +
                    "LEFT JOIN motor_depot.roles as driver_roles on driver_roles.id = drivers.role_id ";

    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public Set<Order> execute() throws SpecificationException {

        return null;
    }
}
