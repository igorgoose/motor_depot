package by.schepov.motordepot.exception.validator.user;

public class PasswordRepetitionException extends Exception {
    public PasswordRepetitionException() {
    }

    public PasswordRepetitionException(String message) {
        super(message);
    }

    public PasswordRepetitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordRepetitionException(Throwable cause) {
        super(cause);
    }
}
