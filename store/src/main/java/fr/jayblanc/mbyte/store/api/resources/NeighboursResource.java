package fr.jayblanc.mbyte.store.api.resources;

import fr.jayblanc.mbyte.store.auth.AuthenticationService;
import fr.jayblanc.mbyte.store.topology.TopologyConfig;
import fr.jayblanc.mbyte.store.topology.TopologyException;
import fr.jayblanc.mbyte.store.topology.TopologyService;
import fr.jayblanc.mbyte.store.topology.entity.Neighbour;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("network")
public class NeighboursResource {

    private static final Logger LOGGER = Logger.getLogger(NeighboursResource.class.getName());

    @Inject TopologyConfig config;
    @Inject TopologyService neighbourhood;
    @Inject AuthenticationService auth;
    @Inject Template network;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Neighbour> getNeighbours() throws TopologyException {
        LOGGER.log(Level.INFO, "GET /api/network");
        return neighbourhood.list();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getNeighboursHtml() throws TopologyException {
        LOGGER.log(Level.INFO, "GET /api/network (html)");
        return network.data("profile", auth.getConnectedProfile()).data("neighbours", neighbourhood.list());
    }
}
