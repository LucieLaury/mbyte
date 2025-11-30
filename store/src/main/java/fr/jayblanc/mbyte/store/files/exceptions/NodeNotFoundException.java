package fr.jayblanc.mbyte.store.files.exceptions;

public class NodeNotFoundException extends Exception {
    public NodeNotFoundException(String message) {
        super(message);
    }

    public NodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
