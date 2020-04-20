package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.jsp.RequestAttribute;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

public class ReportOrderCompletion implements Executable {

    private final OrderRepositoryService orderService = OrderRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ReportOrderCompletion.class);

    ReportOrderCompletion(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        if (user == null) {
            LOGGER.warn("Null user was provided by session!");
            return Page.HOME;
        }
        try{
            int orderId = Integer.parseInt(request.getParameter(JSPParameter.ORDER_ID.getName()));
            Order foundOrder= orderService.getOrderById(orderId);
            if(foundOrder == null){
                LOGGER.warn("Order hasn't been found by id " + orderId);
                return Page.ERROR;
            }
            request.setAttribute(RequestAttribute.ORDER.getName(), foundOrder);
        } catch (OrderServiceException e) {
            LOGGER.warn(e);
            return Page.ERROR;
        }
        return Page.FINISH_ORDER;
    }
}
