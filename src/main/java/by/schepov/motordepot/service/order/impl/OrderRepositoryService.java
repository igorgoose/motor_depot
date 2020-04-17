package by.schepov.motordepot.service.order.impl;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.repository.impl.order.OrderRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.specification.impl.order.FindOrdersByUserIdSpecification;
import by.schepov.motordepot.specification.impl.order.GetAllOrdersSpecification;
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
            return repository.execute(new GetAllOrdersSpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public Set<Order> getOrdersByUserId(int id) throws OrderServiceException {
        try {
            return repository.execute(new FindOrdersByUserIdSpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

}
