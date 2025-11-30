package fr.jayblanc.mbyte.store.files.exceptions;

public class NodePersistenceException extends Exception {

    public NodePersistenceException(String message) {
        super(message);
    }

    public NodePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
