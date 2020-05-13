package by.schepov.motordepot.exception.service;

import by.schepov.motordepot.exception.MessageKeyException;

public class RequestServiceException extends MessageKeyException {

    public RequestServiceException() {
    }

    public RequestServiceException(String message) {
        super(message);
    }

    public RequestServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestServiceException(Throwable cause) {
        super(cause);
    }


}
