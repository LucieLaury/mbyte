package fr.jayblanc.mbyte.store.index;

import java.util.List;

public interface IndexStoreService {

    void index(IndexableContent object) throws IndexStoreException;

    void remove(String identifier) throws IndexStoreException;

    List<IndexStoreResult> search(String scope, String query) throws IndexStoreException;

}
