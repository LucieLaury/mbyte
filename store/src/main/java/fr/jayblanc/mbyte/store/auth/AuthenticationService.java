package fr.jayblanc.mbyte.store.auth;

import fr.jayblanc.mbyte.store.auth.entity.Account;

public interface AuthenticationService {

    String UNAUTHENTIFIED_IDENTIFIER = "anonymous";
    Account ANONYMOUS_PROFILE = new Account(AuthenticationService.UNAUTHENTIFIED_IDENTIFIER, "anonymous", "Anonymous User", "user@anonymous.org", false);

    boolean isAuthentified();

    String getConnectedIdentifier();

    Account getConnectedProfile();

}

