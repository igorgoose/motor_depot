package by.schepov.motordepot.exception.validator;

public class InvalidUserEmailException extends Exception {
    public InvalidUserEmailException() {
    }

    public InvalidUserEmailException(String message) {
        super(message);
    }

    public InvalidUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserEmailException(Throwable cause) {
        super(cause);
    }
}
