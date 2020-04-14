package by.schepov.motordepot.service.order;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.service.OrderServiceException;

import java.util.Set;

public interface OrderService {
    Set<Order> getAllOrders() throws OrderServiceException;
    Set<Order> getOrdersByUserId(int id) throws OrderServiceException;
}
