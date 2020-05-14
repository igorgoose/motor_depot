package by.schepov.motordepot.exception.validator;

public class InvalidUsernameOrPasswordException extends Exception{
    public InvalidUsernameOrPasswordException() {
    }

    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }

    public InvalidUsernameOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUsernameOrPasswordException(Throwable cause) {
        super(cause);
    }
}
