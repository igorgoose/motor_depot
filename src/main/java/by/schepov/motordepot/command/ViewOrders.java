package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ViewOrders implements Executable {

    private final OrderRepositoryService orderService = OrderRepositoryService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ViewOrders.class);
    private static final String BUNDLE_NAME = "locale";

    ViewOrders(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            Set<Order> orders = orderService.getAllOrders();
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
