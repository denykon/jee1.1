package by.gsu.epamlab.model.exceptions;

/**
 * jee1
 *
 * @author Dzianis Kanavalau on 04.02.2015.
 * @version v1.0
 */
public class UserAddingException extends Exception {
    private static final long serialVersionUID = -4447141106735888191L;

    public UserAddingException() {
    }

    public UserAddingException(String message) {
        super(message);
    }

    public UserAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAddingException(Throwable cause) {
        super(cause);
    }
}
