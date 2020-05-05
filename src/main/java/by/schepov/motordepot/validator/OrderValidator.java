package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.exception.OrderValidatorException;

public class OrderValidator {

    private OrderValidator(){}

    public static void validateOrder(Order order) throws OrderValidatorException {
        if(order.getUser() == null || order.getCar() == null ||
                isEmptyString(order.getArrivalLocation()) || isEmptyString(order.getDepartureLocation())){
            throw new OrderValidatorException("Order data is invalid: " + order);
        }
    }

    private static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }

}
