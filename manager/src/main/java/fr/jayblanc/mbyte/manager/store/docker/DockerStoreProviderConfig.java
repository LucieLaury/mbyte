package fr.jayblanc.mbyte.manager.store.docker;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "manager.store.provider.docker")
public interface DockerStoreProviderConfig {

    String server();

}
