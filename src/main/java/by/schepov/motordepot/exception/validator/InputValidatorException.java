package by.schepov.motordepot.exception.validator;

public class InputValidatorException extends Exception{
    public InputValidatorException() {
    }

    public InputValidatorException(String message) {
        super(message);
    }

    public InputValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputValidatorException(Throwable cause) {
        super(cause);
    }
}
