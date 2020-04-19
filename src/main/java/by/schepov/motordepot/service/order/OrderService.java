package by.schepov.motordepot.service.order;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.service.OrderServiceException;

import java.util.Set;

public interface OrderService {
    void insertOrder(Order order) throws OrderServiceException;
    Set<Order> getAllOrders() throws OrderServiceException;
    Set<Order> getOrdersByUserId(int id) throws OrderServiceException;
    Set<Order> getOrdersByDriverIdAndIsCompleted(int id, boolean isCompleted)throws OrderServiceException;
}
