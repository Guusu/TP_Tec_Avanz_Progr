package ar.com.up.theater.manager.exception;

public class InvalidHallException extends TheaterException {
    public InvalidHallException(String message) {
        super(message);
    }

    public InvalidHallException(String code, String message) {
        super(code, message);
    }

    public InvalidHallException(String message, String code, String message1) {
        super(message, code, message1);
    }

    public InvalidHallException(String message, Throwable cause, String code, String message1) {
        super(message, cause, code, message1);
    }

    public InvalidHallException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
