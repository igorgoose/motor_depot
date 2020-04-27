package by.schepov.motordepot.exception;

public class OrderValidatorException extends Exception {
    public OrderValidatorException() {
    }

    public OrderValidatorException(String message) {
        super(message);
    }

    public OrderValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderValidatorException(Throwable cause) {
        super(cause);
    }
}
