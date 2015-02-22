package by.gsu.epamlab.model.exceptions;

public class DAOException extends Exception {
    private static final long serialVersionUID = 2702941858321518528L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
