package fr.jayblanc.mbyte.manager.store;

import java.util.List;

public interface StoreProvider {

    String name();

    List<String> listApps() throws StoreProviderException;

    String createApp(String id, String owner, String name) throws StoreProviderException;

    void destroyApp(String id) throws StoreProviderException;

}
