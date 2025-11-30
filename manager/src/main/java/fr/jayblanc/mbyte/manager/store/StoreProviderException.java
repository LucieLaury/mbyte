package fr.jayblanc.mbyte.manager.store;

public class StoreProviderException extends Exception {

    public StoreProviderException(String message) {
        super(message);
    }

    public StoreProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}
