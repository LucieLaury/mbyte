package fr.jayblanc.mbyte.store.notification;

public interface NotificationService {

    String NOTIFICATION_TOPIC = "notification";

    void notify(String type, String source) throws NotificationServiceException;

}
