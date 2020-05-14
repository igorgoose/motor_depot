package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.parameter.SessionAttribute;
import by.schepov.motordepot.service.order.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewCompletedOrders extends RepositoryAction {

    private final OrderService orderService = serviceFactory.createOrderService();
    private static final Logger LOGGER = LogManager.getLogger(ViewCompletedOrders.class);
    private static final String BUNDLE_NAME = "locale";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
        try{
            Set<Order> orders = orderService.getOrdersByDriverIdAndIsCompleted(user.getId(), true);
            request.setAttribute(RequestAttribute.ORDERS.getName(), orders);
        } catch (OrderServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.MANAGEMENT_ORDERS;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }
}
