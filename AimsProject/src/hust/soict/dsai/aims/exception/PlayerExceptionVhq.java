package hust.soict.dsai.aims.exception;

public class PlayerExceptionVhq extends Exception {

    // Default constructor
    public PlayerExceptionVhq() {
        super();  // Call the parent class (Exception) constructor
    }

    // Constructor with an error message
    public PlayerExceptionVhq(String message) {
        super(message);  // Call the parent class constructor and pass the error message
    }

    // Constructor with an error message and the underlying cause (Throwable)
    public PlayerExceptionVhq(String message, Throwable cause) {
        super(message, cause);  // Call the parent class constructor with both the message and cause
    }

    // Constructor with the underlying cause
    public PlayerExceptionVhq(Throwable cause) {
        super(cause);  // Call the parent class constructor with the cause
    }
}
