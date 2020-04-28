package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

public class ViewUserOrders implements Executable {
    private static final Logger LOGGER = LogManager.getLogger(ViewUserDetails.class);

    //todo create ServiceFactory
    private final UserService userService = UserRepositoryService.getInstance();
    private final OrderService orderService = OrderRepositoryService.getInstance();

    ViewUserOrders(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int user_id = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getName()));
            User foundUser = userService.getUserById(user_id);
            if (foundUser == null) {
                LOGGER.warn("No users have been found by id " + user_id);
                return Page.ERROR;
            }
            request.setAttribute(RequestAttribute.USER.getName(), foundUser);
            Set<Order> orders = orderService.getOrdersByUserId(user_id);
            request.setAttribute(RequestAttribute.ORDERS.getName(), orders);
        } catch (UserServiceException | OrderServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.USER_DETAILS;
    }

}
