package fr.jayblanc.mbyte.store.auth;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "store.auth")
public interface AuthenticationConfig {
    String owner();
}
