package ar.com.up.theater.user.management.exception;

public class TheaterException extends RuntimeException {
    String code;
    String message;
    public TheaterException(String message) {
        super(message);
    }

  public TheaterException(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public TheaterException(String message, String code, String message1) {
    super(message);
    this.code = code;
    this.message = message1;
  }

  public TheaterException(String message, Throwable cause, String code, String message1) {
    super(message, cause);
    this.code = code;
    this.message = message1;
  }

  public TheaterException(Throwable cause, String code, String message) {
    super(cause);
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
