package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.service.request.impl.RequestRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewProfile implements Executable {

    private final OrderService orderService = OrderRepositoryService.getInstance();
    private final RequestService requestService = RequestRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ViewProfile.class);

    ViewProfile(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if(user == null){
            LOGGER.error("Null user was provided by session!");
            return Page.ERROR;
        }
        try {
            int id = user.getId();
            Set<Order> orders = orderService.getOrdersByUserId(id);
            Set<Request> requests = requestService.getRequestsByUserId(id);
            request.setAttribute(RequestAttribute.REQUESTS.getName(), requests);
            request.setAttribute(RequestAttribute.ORDERS.getName(), orders);
        } catch (OrderServiceException | RequestServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.USER_DETAILS;
    }
}
