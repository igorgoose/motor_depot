package by.schepov.motordepot.exception.validator;

public class CarValidatorException extends Exception {
    public CarValidatorException() {
    }

    public CarValidatorException(String message) {
        super(message);
    }

    public CarValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarValidatorException(Throwable cause) {
        super(cause);
    }
}
