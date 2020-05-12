package by.schepov.motordepot.exception.service;

import by.schepov.motordepot.exception.MessageKeyException;

public class CarServiceException extends MessageKeyException {

    public CarServiceException() {
    }

    public CarServiceException(String message) {
        super(message);
    }

    public CarServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarServiceException(Throwable cause) {
        super(cause);
    }

}
