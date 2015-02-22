package by.gsu.epamlab.model.exceptions;

public class FileCheckException extends Exception {
    private static final long serialVersionUID = -656371542079167666L;

    public FileCheckException() {
        super();
    }

    public FileCheckException(String message) {
        super(message);
    }

    public FileCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
