package fr.jayblanc.mbyte.store.api.filter;

import fr.jayblanc.mbyte.store.auth.AuthenticationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getName());

    @Context ResourceInfo resourceInfo;
    @Inject AuthenticationService authenticationService;

    @Override
    public void filter(ContainerRequestContext ctx) {
        LOGGER.log(Level.INFO, "Applying security filter");
        if ( resourceInfo.getResourceMethod().isAnnotationPresent(OnlyOwner.class) || resourceInfo.getResourceClass().isAnnotationPresent(OnlyOwner.class) ) {
            LOGGER.log(Level.INFO, "Owner annotation present, checking if connected Profile is this store owner");
            if ( !authenticationService.getConnectedProfile().isOwner() ) {
                //TODO if accept is html, use a template for unauthorized
                ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }

}
