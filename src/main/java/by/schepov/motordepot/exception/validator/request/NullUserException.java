package by.schepov.motordepot.exception.validator.request;

public class NullUserException extends Exception {
    public NullUserException() {
    }

    public NullUserException(String message) {
        super(message);
    }

    public NullUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullUserException(Throwable cause) {
        super(cause);
    }
}
