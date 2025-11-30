package fr.jayblanc.mbyte.store.api.exception;

import fr.jayblanc.mbyte.store.api.StoreAPI;
import fr.jayblanc.mbyte.store.api.dto.ErrorDto;
import fr.jayblanc.mbyte.store.files.exceptions.NodeNotEmptyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class NodeNotEmptyExceptionMapper implements ExceptionMapper<NodeNotEmptyException> {

    private static final Logger LOGGER = Logger.getLogger(StoreAPI.class.getName());

    @Override
    public Response toResponse(NodeNotEmptyException e) {
        ErrorDto dto = new ErrorDto("node.not-empty", e.getMessage(), e);
        LOGGER.log(Level.INFO, "ERROR [" + dto.getId() + "] " + dto);
        return Response.status(Response.Status.BAD_REQUEST).entity(dto).build();
    }
}
