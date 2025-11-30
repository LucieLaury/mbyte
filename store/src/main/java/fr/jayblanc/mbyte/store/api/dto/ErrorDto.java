package fr.jayblanc.mbyte.store.api.dto;

import java.util.UUID;

public class ErrorDto {

    private String id;
    private String key;
    private String message;
    private Exception exception;

    public ErrorDto() {
        this.id = UUID.randomUUID().toString();
    }

    public ErrorDto(String key, String message) {
        this();
        this.key = key;
        this.message = message;
    }

    public ErrorDto(String key, String message, Exception exception) {
        this();
        this.key = key;
        this.message = message;
        this.exception = exception;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", message='" + message + '\'' +
                ", exception=" + exception +
                '}';
    }
}
