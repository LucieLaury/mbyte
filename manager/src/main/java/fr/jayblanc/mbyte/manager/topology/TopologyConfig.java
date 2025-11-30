package fr.jayblanc.mbyte.manager.topology;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "manager.topology")
public interface TopologyConfig {

    boolean https();
    String host();
    int port();

}
