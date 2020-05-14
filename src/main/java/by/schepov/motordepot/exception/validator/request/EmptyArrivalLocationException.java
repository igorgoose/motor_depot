package by.schepov.motordepot.exception.validator.request;

public class EmptyArrivalLocationException extends Exception {

    public EmptyArrivalLocationException() {
    }

    public EmptyArrivalLocationException(String message) {
        super(message);
    }

    public EmptyArrivalLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyArrivalLocationException(Throwable cause) {
        super(cause);
    }

}
