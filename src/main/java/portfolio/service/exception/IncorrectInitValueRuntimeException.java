package portfolio.service.exception;

public class IncorrectInitValueRuntimeException extends RuntimeException {
    public IncorrectInitValueRuntimeException() {
    }

    public IncorrectInitValueRuntimeException(String message) {
        super(message);
    }

    public IncorrectInitValueRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
