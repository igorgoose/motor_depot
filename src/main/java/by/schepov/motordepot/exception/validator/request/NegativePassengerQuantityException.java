package by.schepov.motordepot.exception.validator.request;

public class NegativePassengerQuantityException extends Exception {
    public NegativePassengerQuantityException() {
    }

    public NegativePassengerQuantityException(String message) {
        super(message);
    }

    public NegativePassengerQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativePassengerQuantityException(Throwable cause) {
        super(cause);
    }
}
