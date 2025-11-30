package fr.jayblanc.mbyte.store.api.resources;

import fr.jayblanc.mbyte.store.search.SearchResult;
import fr.jayblanc.mbyte.store.search.SearchService;
import fr.jayblanc.mbyte.store.search.SearchServiceException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("search")
public class SearchResource {

    private static final Logger LOGGER = Logger.getLogger(SearchResource.class.getName());

    @Inject SearchService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SearchResult> search(@QueryParam("q") String query) throws SearchServiceException {
        LOGGER.log(Level.INFO, "GET /api/search");
        return service.search(query);
    }
}
