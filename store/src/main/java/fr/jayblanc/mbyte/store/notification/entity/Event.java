package fr.jayblanc.mbyte.store.notification.entity;

import java.util.UUID;

public class Event {

    private String id;
    private long timestamp;
    private String eventType;
    private String sourceId;

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public static Event build(String type, String sourceId) {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setTimestamp(System.currentTimeMillis());
        event.setEventType(type);
        event.setSourceId(sourceId);
        return event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                ", sourceId='" + sourceId + '\'' +
                '}';
    }

}
