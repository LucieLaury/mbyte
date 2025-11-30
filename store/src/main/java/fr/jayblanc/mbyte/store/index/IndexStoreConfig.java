package fr.jayblanc.mbyte.store.index;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "store.index")
public interface IndexStoreConfig {
    String home();
}
