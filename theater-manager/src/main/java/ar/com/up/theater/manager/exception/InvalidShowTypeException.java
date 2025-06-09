package ar.com.up.theater.manager.exception;

public class InvalidShowTypeException extends TheaterException {
    public InvalidShowTypeException(String message) {
        super(message);
    }

    public InvalidShowTypeException(String code, String message) {
        super(code, message);
    }

    public InvalidShowTypeException(String message, String code, String message1) {
        super(message, code, message1);
    }

    public InvalidShowTypeException(String message, Throwable cause, String code, String message1) {
        super(message, cause, code, message1);
    }

    public InvalidShowTypeException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
