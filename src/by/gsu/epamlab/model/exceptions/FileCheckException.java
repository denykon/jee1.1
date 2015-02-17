package by.gsu.epamlab.model.exceptions;

public class FileCheckException extends Exception {
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
