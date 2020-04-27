package by.schepov.motordepot.exception;

public class RequestValidatorException extends Exception {

    public RequestValidatorException() {
    }

    public RequestValidatorException(String message) {
        super(message);
    }

    public RequestValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestValidatorException(Throwable cause) {
        super(cause);
    }
}
