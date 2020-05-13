package by.schepov.motordepot.exception.service;

import by.schepov.motordepot.exception.MessageKeyException;

public class OrderServiceException extends MessageKeyException {
    public OrderServiceException() {
    }

    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderServiceException(Throwable cause) {
        super(cause);
    }
}
