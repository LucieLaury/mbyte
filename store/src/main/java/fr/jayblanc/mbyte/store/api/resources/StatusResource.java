package fr.jayblanc.mbyte.store.api.resources;

import fr.jayblanc.mbyte.store.api.dto.Status;
import fr.jayblanc.mbyte.store.auth.AuthenticationService;
import fr.jayblanc.mbyte.store.metrics.MetricsService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jerome Blanchard
 */
@Path("/status")
public class StatusResource {

    private static final Logger LOGGER = Logger.getLogger(StatusResource.class.getName());

    @Inject Template status;
    @Inject AuthenticationService auth;
    @Inject MetricsService metrics;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Status getStatus() {
        LOGGER.log(Level.INFO, "GET /api/status");
        return Status.fromRuntime().withConnectedId(auth.getConnectedProfile().getId());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        LOGGER.log(Level.INFO, "GET /api/status (html)");
        return status.data("profile", auth.getConnectedProfile()).data("status", Status.fromRuntime().withMetrics(metrics));
    }

}

