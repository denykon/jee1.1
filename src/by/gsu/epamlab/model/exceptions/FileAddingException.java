package by.gsu.epamlab.model.exceptions;

/**
 * jee1
 *
 * @author Dzianis Kanavalau on 14.02.2015.
 * @version v1.0
 */
public class FileAddingException extends Exception {
    private static final long serialVersionUID = 6814553447016159120L;

    public FileAddingException() {
        super();
    }

    public FileAddingException(String message) {
        super(message);
    }

    public FileAddingException(String message, Throwable cause) {
        super(message, cause);
    }
}
