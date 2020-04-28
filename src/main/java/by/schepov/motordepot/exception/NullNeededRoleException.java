package by.schepov.motordepot.exception;

public class NullNeededRoleException extends RuntimeException {
    public NullNeededRoleException() {
    }

    public NullNeededRoleException(String message) {
        super(message);
    }

    public NullNeededRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullNeededRoleException(Throwable cause) {
        super(cause);
    }
}
