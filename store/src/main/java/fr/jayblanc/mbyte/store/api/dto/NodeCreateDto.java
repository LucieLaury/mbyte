package fr.jayblanc.mbyte.store.api.dto;

import fr.jayblanc.mbyte.store.api.validation.Filename;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

public class NodeCreateDto {

    @FormParam("name")
    @PartType(MediaType.TEXT_PLAIN)
    @Filename
    private String name;
    @FormParam("data")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream data = null;
    @Size(max=2000000, message="content size is 2Mo max, for larger content use a multipart-form-data with data parameter")
    private byte[] content;

    public NodeCreateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
