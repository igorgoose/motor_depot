package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.validator.request.*;

public class RequestValidator {

    private RequestValidator() {
    }

    public static void validateRequest(Request request) throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NegativePassengerQuantityException, NullUserException {
        if (isEmptyString(request.getArrivalLocation())){
            throw new EmptyArrivalLocationException();
        }
        if (isEmptyString(request.getDepartureLocation())){
            throw new EmptyDepartureLocationException();
        }
        if (request.getLoad() < 0 ) {
            throw new NegativeLoadException();
        }
        if (request.getPassengersQuantity() < 0) {
            throw new NegativePassengerQuantityException();
        }
        if (request.getUser() == null) {
            throw new NullUserException();
        }
    }

    private static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }

}
