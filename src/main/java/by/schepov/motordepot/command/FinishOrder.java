package by.schepov.motordepot.command;

import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.service.CarServiceException;
import by.schepov.motordepot.exception.service.OrderServiceException;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.SessionAttribute;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.service.car.impl.CarRepositoryService;
import by.schepov.motordepot.service.order.OrderService;
import by.schepov.motordepot.service.order.impl.OrderRepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.service.user.impl.UserRepositoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FinishOrder implements Executable {

    private static final Logger LOGGER = LogManager.getLogger(ReportOrderCompletion.class);
    private final OrderService orderService = OrderRepositoryService.getInstance();
    private final CarService carService = CarRepositoryService.getInstance();
    private final UserService userService = UserRepositoryService.getInstance();
    private static final String BUNDLE_NAME = "locale";

    FinishOrder(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER.getName());
            int orderId = Integer.parseInt(request.getParameter(JSPParameter.ORDER_ID.getName()));
            CarStatus carStatus = CarStatus.valueOf(request.getParameter(JSPParameter.CAR_STATE.getName()));
            Order foundOrder= orderService.getOrderById(orderId);
            if(foundOrder == null){
                LOGGER.warn("Order hasn't been found by id " + orderId);
                setMessage(request.getSession(), MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            userService.updateStatusById(user.getId(), UserStatus.ACTIVE);
            carService.updateCarStatus(foundOrder.getCar().getId(), carStatus);
            orderService.updateOrderStatus(orderId, true);
        } catch (OrderServiceException | CarServiceException | UserServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request.getSession(), e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
        return Page.MANAGEMENT;
    }

    private void setMessage(HttpSession session, MessageKey messageKey){
        setMessage(session, messageKey, BUNDLE_NAME);
    }
}
