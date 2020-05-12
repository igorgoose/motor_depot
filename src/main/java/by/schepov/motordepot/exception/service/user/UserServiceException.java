package by.schepov.motordepot.exception.service.user;

import by.schepov.motordepot.exception.MessageKeyException;

public class UserServiceException extends MessageKeyException {

    public UserServiceException() {
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }


}
