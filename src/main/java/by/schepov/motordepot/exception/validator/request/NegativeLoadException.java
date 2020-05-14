package by.schepov.motordepot.exception.validator.request;

public class NegativeLoadException extends Exception {

    public NegativeLoadException() {
    }

    public NegativeLoadException(String message) {
        super(message);
    }

    public NegativeLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeLoadException(Throwable cause) {
        super(cause);
    }

}
