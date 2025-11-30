package fr.jayblanc.mbyte.store.notification.listener;

import fr.jayblanc.mbyte.store.notification.NotificationService;
import fr.jayblanc.mbyte.store.notification.entity.Event;
import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class LoggerEventListener {

    private static final Logger LOGGER = Logger.getLogger(LoggerEventListener.class.getName());

    @ConsumeEvent(NotificationService.NOTIFICATION_TOPIC)
    public void onMessage(Event event) {
        LOGGER.log(Level.INFO, "Event received: " + event.toString());
    }

}
