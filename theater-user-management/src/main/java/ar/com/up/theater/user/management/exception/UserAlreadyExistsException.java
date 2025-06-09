package ar.com.up.theater.user.management.exception;

public class UserAlreadyExistsException extends TheaterException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String code, String message) {
        super(message,code, message);
    }

    public UserAlreadyExistsException(String message, String code, String message1) {
        super(message, code, message1);
    }

    public UserAlreadyExistsException(String message, Throwable cause, String code, String message1) {
        super(message, cause, code, message1);
    }

    public UserAlreadyExistsException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
