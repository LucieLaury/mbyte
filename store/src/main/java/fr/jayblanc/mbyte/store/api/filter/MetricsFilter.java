package fr.jayblanc.mbyte.store.api.filter;

import fr.jayblanc.mbyte.store.metrics.MetricsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MetricsFilter implements ContainerRequestFilter {

    @Inject MetricsService metrics;

    @Override
    public void filter(ContainerRequestContext ctx) {
        if (metrics.getLatestMetric("upload") > 20) {
            ctx.abortWith(Response.status(Response.Status.TOO_MANY_REQUESTS).build());
        }
    }

}
