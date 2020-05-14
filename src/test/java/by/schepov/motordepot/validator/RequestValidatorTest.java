package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.validator.request.*;
import org.junit.Before;
import org.junit.Test;

public class RequestValidatorTest {

    private Request request;

    @Before
    public void initRequest(){
        request = new Request();
        request.setId(1);
        request.setArrivalLocation("arrivalLocation");
        request.setDepartureLocation("departureLocation");
        request.setUser(new User());
        request.setLoad(1);
        request.setPassengersQuantity(1);
    }

    @Test
    public void validateRequestPositive() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        RequestValidator.validateRequest(request);
    }

    @Test(expected = EmptyArrivalLocationException.class)
    public void validateRequestNullArrivalLocation() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setArrivalLocation(null);
        RequestValidator.validateRequest(request);
    }

    @Test(expected = EmptyArrivalLocationException.class)
    public void validateRequestEmptyArrivalLocation() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setArrivalLocation("");
        RequestValidator.validateRequest(request);
    }

    @Test(expected = EmptyDepartureLocationException.class)
    public void validateRequestNullDepartureLocation() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setDepartureLocation(null);
        RequestValidator.validateRequest(request);
    }

    @Test(expected = EmptyDepartureLocationException.class)
    public void validateRequestEmptyDepartureLocation() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setDepartureLocation("");
        RequestValidator.validateRequest(request);
    }

    @Test(expected = NegativeLoadException.class)
    public void validateRequestNegativeLoad() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setLoad(-1);
        RequestValidator.validateRequest(request);
    }

    @Test(expected = NegativePassengerQuantityException.class)
    public void validateRequestNegativePassengerQuantity() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setPassengersQuantity(-1);
        RequestValidator.validateRequest(request);
    }

    @Test(expected = NullUserException.class)
    public void validateRequestNullUser() throws EmptyArrivalLocationException, EmptyDepartureLocationException, NegativeLoadException, NullUserException, NegativePassengerQuantityException {
        request.setUser(null);
        RequestValidator.validateRequest(request);
    }

}