package fr.jayblanc.mbyte.store.data.exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
