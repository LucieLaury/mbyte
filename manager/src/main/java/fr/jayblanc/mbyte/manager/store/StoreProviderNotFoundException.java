package fr.jayblanc.mbyte.manager.store;

public class StoreProviderNotFoundException extends Exception {

    public StoreProviderNotFoundException(String message) {
        super(message);
    }

    public StoreProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
