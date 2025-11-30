package fr.jayblanc.mbyte.manager.store;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "manager.store")
public interface StoreProviderConfig {

    String provider();

}
