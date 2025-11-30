package fr.jayblanc.mbyte.manager.core;

public class StoreNotFoundException extends Exception {
    public StoreNotFoundException(String owner) {
        super("Unable to find a store for owner: " + owner);
    }
}
