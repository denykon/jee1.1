package by.gsu.epamlab.model.exceptions;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 7118783405101866634L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
