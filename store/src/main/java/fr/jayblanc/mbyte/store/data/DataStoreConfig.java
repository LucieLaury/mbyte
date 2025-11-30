package fr.jayblanc.mbyte.store.data;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "store.data")
public interface DataStoreConfig {
    String home();
}
