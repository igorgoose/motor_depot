package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
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
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try{
            int id = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getName()));
            Set<User> users = userService.getUsersById(id);
            Iterator<User> iterator = users.iterator();
            if(iterator.hasNext()){
                request.setAttribute(RequestAttribute.USER.getName(), iterator.next());
            } else {
                return Page.ERROR;
            }
            Set<Order> orders = orderService.getOrdersByUserId(id);
            request.setAttribute(RequestAttribute.ORDERS.getName(), orders);
        } catch (UserServiceException | OrderServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.USER_DETAILS;
    }

}
