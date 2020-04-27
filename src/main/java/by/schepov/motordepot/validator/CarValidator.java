package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.exception.CarValidatorException;

public class CarValidator {

    private static final String REGISTRATION_NUMBER_REGEX = "^[0-9]{4}[A-Z]{2}[1-7]$";

    private CarValidator() {
    }

    public static void validateCar(Car car) throws CarValidatorException {
        if (car.getDriver() == null || isValidRegistrationNumber(car.getRegistrationNumber()) ||
                car.getCarStatus() == null || car.getCarName() == null || car.getLoadCapacity() < 0 ||
                car.getPassengerCapacity() < 0) {
            throw new CarValidatorException("Car data is invalid: " + car);
        }
    }

    private static boolean isValidRegistrationNumber(String registrationNumber) {
        return registrationNumber != null && registrationNumber.matches(REGISTRATION_NUMBER_REGEX);
    }

}
