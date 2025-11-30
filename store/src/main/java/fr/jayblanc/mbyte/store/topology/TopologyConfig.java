package fr.jayblanc.mbyte.store.topology;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "store.topology")
public interface TopologyConfig {

    boolean enabled();
    boolean https();
    String host();
    int port();
    Service service();

    interface Service {
        String name();
        String protocol();
        String host();
        int port();
    }
}
