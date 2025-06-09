package ar.com.up.theater.manager.exception;

public class InvalidArtistException extends TheaterException {
    public InvalidArtistException(String message) {
        super(message);
    }

    public InvalidArtistException(String code, String message) {
        super(code, message);
    }

    public InvalidArtistException(String message, String code, String message1) {
        super(message, code, message1);
    }

    public InvalidArtistException(String message, Throwable cause, String code, String message1) {
        super(message, cause, code, message1);
    }

    public InvalidArtistException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
