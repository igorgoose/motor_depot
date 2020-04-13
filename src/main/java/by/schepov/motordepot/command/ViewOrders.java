package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewOrders implements Executable {

    private final OrderRepositoryService orderService = OrderRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ViewOrders.class);

    ViewOrders(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.error("Null user was provided by session!");
            return Page.ERROR;
        }
        try{
            Set<Order> orders = orderService.getAllOrders();
            request.setAttribute(RequestAttribute.USERNAME.getName(), user.getUsername());
            request.setAttribute(RequestAttribute.ROLE.getName(), user.getRole().getId());
            request.setAttribute(RequestAttribute.ORDERS.getName(), orders);
            request.setAttribute(RequestAttribute.MANAGEMENT_REQUEST.getName(), Command.VIEW_ORDERS.getName());
        } catch (OrderServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.MANAGEMENT;
    }
}
