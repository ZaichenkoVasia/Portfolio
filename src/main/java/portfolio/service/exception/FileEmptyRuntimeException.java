package portfolio.service.exception;

public class FileEmptyRuntimeException extends RuntimeException {
    public FileEmptyRuntimeException() {
    }

    public FileEmptyRuntimeException(String message) {
        super(message);
    }

    public FileEmptyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
