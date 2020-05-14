package by.schepov.motordepot.exception.validator.user;

public class UserStatusIsNullOrBlockedException extends Exception {
    public UserStatusIsNullOrBlockedException() {
    }

    public UserStatusIsNullOrBlockedException(String message) {
        super(message);
    }

    public UserStatusIsNullOrBlockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserStatusIsNullOrBlockedException(Throwable cause) {
        super(cause);
    }
}
