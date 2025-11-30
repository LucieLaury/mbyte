package fr.jayblanc.mbyte.manager.auth;

import fr.jayblanc.mbyte.manager.auth.entity.Profile;

public interface AuthenticationService {

    String UNAUTHENTIFIED_IDENTIFIER = "anonymous";

    boolean isAuthentified();

    String getConnectedIdentifier();

    boolean isConnectedIdentifierInRoleAdmin();

    Profile getConnectedProfile();

}
