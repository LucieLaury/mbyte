package fr.jayblanc.mbyte.manager.core;

import fr.jayblanc.mbyte.manager.core.entity.Store;

public interface CoreService {

    Store createStore(String name);

    Store getConnectedUserStore() throws StoreNotFoundException, CoreServiceException;

}
