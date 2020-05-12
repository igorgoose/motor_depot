package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.validator.RequestValidatorException;

public class RequestValidator {

    private RequestValidator() {
    }

    public static void validateRequest(Request request) throws RequestValidatorException {
        if (isEmptyString(request.getArrivalLocation()) || isEmptyString(request.getDepartureLocation()) ||
                request.getLoad() < 0 || request.getPassengersQuantity() < 0 || request.getUser() == null) {
                throw new RequestValidatorException("Request data is invalid:" + request);
        }
    }

    private static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }

}
