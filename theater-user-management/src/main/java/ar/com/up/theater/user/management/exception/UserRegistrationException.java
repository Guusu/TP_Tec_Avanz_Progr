package ar.com.up.theater.user.management.exception;

public class UserRegistrationException extends TheaterException {

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String code, String message) {
        super(message,code, message);
    }

    public UserRegistrationException(String message, String code, String message1) {
        super(message, code, message1);
    }

    public UserRegistrationException(String message, Throwable cause, String code, String message1) {
        super(message, cause, code, message1);
    }

    public UserRegistrationException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
