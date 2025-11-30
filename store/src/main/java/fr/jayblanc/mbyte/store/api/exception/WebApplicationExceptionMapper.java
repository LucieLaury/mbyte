package fr.jayblanc.mbyte.store.api.exception;

import fr.jayblanc.mbyte.store.api.StoreAPI;
import fr.jayblanc.mbyte.store.api.dto.ErrorDto;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private static final Logger LOGGER = Logger.getLogger(StoreAPI.class.getName());

    @Override
    public Response toResponse(WebApplicationException e) {
        ErrorDto dto = new ErrorDto("unexpected-error", e.getMessage(), e);
        LOGGER.log(Level.SEVERE, dto.getKey().toUpperCase() + " [" + dto.getId() + "] ", dto.getException());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dto).build();
    }
}
