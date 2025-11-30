package fr.jayblanc.mbyte.manager.auth;

import io.smallrye.config.ConfigMapping;

/**
 * @author Jerome Blanchard
 */
@ConfigMapping(prefix = "manager.auth")
public interface AuthenticationConfig {
    String adminRoleName();
}
