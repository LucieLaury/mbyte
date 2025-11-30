package fr.jayblanc.mbyte.manager.core;

public class CoreServiceException extends Exception {
    public CoreServiceException(String message) {
        super(message);
    }

    public CoreServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
