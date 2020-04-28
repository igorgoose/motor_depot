package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.jsp.JSPParameter;
import by.schepov.motordepot.jsp.Page;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.session.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

public class FinishOrder implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ReportOrderCompletion.class);
    private final OrderService orderService = OrderRepositoryService.getInstance();
    private final CarService carService = CarRepositoryService.getInstance();

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
            return Page.ERROR;
        }
        return Page.MANAGEMENT;
    }
}
