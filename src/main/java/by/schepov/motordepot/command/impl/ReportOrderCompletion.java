package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.order.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportOrderCompletion extends RepositoryAction {

    private final OrderService orderService = serviceFactory.createOrderService();
    private static final Logger LOGGER = LogManager.getLogger(ReportOrderCompletion.class);
    private static final String BUNDLE_NAME = "locale";

    ReportOrderCompletion(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int orderId = Integer.parseInt(request.getParameter(JSPParameter.ORDER_ID.getName()));
            Order foundOrder= orderService.getOrderById(orderId);
            if(foundOrder == null){
                LOGGER.warn("Order hasn't been found by id " + orderId);
                setMessage(request, MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            request.setAttribute(RequestAttribute.ORDER.getName(), foundOrder);
        } catch (OrderServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request, e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.FINISH_ORDER;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }

}
