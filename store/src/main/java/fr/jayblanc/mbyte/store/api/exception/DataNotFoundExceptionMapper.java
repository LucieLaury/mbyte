package fr.jayblanc.mbyte.store.api.exception;

import fr.jayblanc.mbyte.store.api.StoreAPI;
import fr.jayblanc.mbyte.store.api.dto.ErrorDto;
import fr.jayblanc.mbyte.store.data.exception.DataNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    private static final Logger LOGGER = Logger.getLogger(StoreAPI.class.getName());

    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorDto dto = new ErrorDto("content.unknown", e.getMessage(), e);
        LOGGER.log(Level.INFO, "ERROR [" + dto.getId() + "] " + dto);
        return Response.status(Response.Status.NOT_FOUND).entity(dto).build();
    }
}
