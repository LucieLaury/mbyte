package fr.jayblanc.mbyte.store.data;

import fr.jayblanc.mbyte.store.data.exception.DataNotFoundException;
import fr.jayblanc.mbyte.store.data.exception.DataStoreException;

import java.io.InputStream;

/**
 * @author Jerome Blanchard
 */
public interface DataStore {

    boolean exists(String key);

    String put(InputStream is) throws DataStoreException;

    InputStream get(String key) throws DataStoreException, DataNotFoundException;

    String type(String key, String name) throws DataStoreException, DataNotFoundException;

    long size(String key) throws DataStoreException, DataNotFoundException;

    String extract(String key, String name, String type) throws DataStoreException, DataNotFoundException;

    void delete(String key) throws DataStoreException;

}
