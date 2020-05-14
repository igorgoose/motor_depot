package by.schepov.motordepot.exception.validator.request;

public class EmptyDepartureLocationException extends Exception {

    public EmptyDepartureLocationException() {
    }

    public EmptyDepartureLocationException(String message) {
        super(message);
    }

    public EmptyDepartureLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDepartureLocationException(Throwable cause) {
        super(cause);
    }

}
