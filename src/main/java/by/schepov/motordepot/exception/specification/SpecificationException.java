package by.schepov.motordepot.exception.specification;

public class SpecificationException extends Exception{
    public SpecificationException() {
    }

    public SpecificationException(String message) {
        super(message);
    }

    public SpecificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecificationException(Throwable cause) {
        super(cause);
    }
}
