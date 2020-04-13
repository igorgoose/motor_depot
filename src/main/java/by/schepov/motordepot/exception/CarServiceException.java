package by.schepov.motordepot.exception;

import by.schepov.motordepot.exception.repository.RepositoryException;

public class CarServiceException extends Exception {
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
