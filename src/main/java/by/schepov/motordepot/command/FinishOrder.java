package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.service.CarServiceException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class FinishOrder implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ReportOrderCompletion.class);
    private final OrderService orderService = OrderRepositoryService.getInstance();
    private final CarService carService = CarRepositoryService.getInstance();
    private static final String BUNDLE_NAME = "locale";

    FinishOrder(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int orderId = Integer.parseInt(request.getParameter(JSPParameter.ORDER_ID.getName()));
            CarStatus carStatus = CarStatus.valueOf(request.getParameter(JSPParameter.CAR_STATE.getName()));
            Order foundOrder= orderService.getOrderById(orderId);
            if(foundOrder == null){
                LOGGER.warn("Order hasn't been found by id " + orderId);

                return Page.ERROR;
            }
            carService.updateCarStatus(foundOrder.getCar().getId(), carStatus);
            orderService.updateOrderStatus(orderId, true);
        } catch (OrderServiceException | CarServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                Locale locale = new Locale((String) request.getSession().getAttribute(SessionAttribute.LOCALE.getName()));
                ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
                request.setAttribute(RequestAttribute.MESSAGE.getName(), bundle.getString(e.getMessageBundleKey().getValue()));
            }
            return Page.ERROR;
        }
        return Page.MANAGEMENT;
    }

    private void setMessage(HttpServletRequest request, MessageKey messageKey){
        setMessage(request, messageKey, BUNDLE_NAME);
    }
}
