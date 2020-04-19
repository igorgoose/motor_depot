package by.schepov.motordepot.service.order.impl;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.repository.impl.order.OrderRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.specification.query.impl.order.FindOrderByDriverIdAndIsCompletedQuerySpecification;
import by.schepov.motordepot.specification.query.impl.order.FindOrderById;
import by.schepov.motordepot.specification.query.impl.order.FindOrdersByUserIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.order.GetAllOrdersQuerySpecification;
import by.schepov.motordepot.specification.update.order.UpdateOrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class OrderRepositoryService extends RepositoryService<Order> implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderRepositoryService.class);

    private OrderRepositoryService() {
        super(OrderRepository.INSTANCE);
    }

    private static class InstanceHolder{
        public static final OrderRepositoryService INSTANCE = new OrderRepositoryService();
    }

    public static OrderRepositoryService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void insertOrder(Order order) throws OrderServiceException {
        try {
            repository.insert(order);
        } catch (RepositoryException e) {
            throw new OrderServiceException(e);
        }
    }

    @Override
    public Set<Order> getAllOrders() throws OrderServiceException {
        try {
            return repository.executeQuery(new GetAllOrdersQuerySpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public Set<Order> getOrdersByUserId(int id) throws OrderServiceException {
        try {
            return repository.executeQuery(new FindOrdersByUserIdQuerySpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public Set<Order> getOrdersByDriverIdAndIsCompleted(int id, boolean isCompleted) throws OrderServiceException {
        try {
            return repository.executeQuery(new FindOrderByDriverIdAndIsCompletedQuerySpecification(id, isCompleted));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public Set<Order> getOrderById(int id) throws OrderServiceException {
        try {
            return repository.executeQuery(new FindOrderById(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public void updateOrderStatus(int orderId, boolean isComplete) throws OrderServiceException {
        try {
            repository.executeUpdate(new UpdateOrderStatus(orderId, isComplete));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

}
