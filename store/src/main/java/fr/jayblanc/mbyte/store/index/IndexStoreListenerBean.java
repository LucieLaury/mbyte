package fr.jayblanc.mbyte.store.index;

import fr.jayblanc.mbyte.store.notification.NotificationService;
import fr.jayblanc.mbyte.store.notification.entity.Event;
import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class IndexStoreListenerBean {

    private static final Logger LOGGER = Logger.getLogger(IndexStoreListenerBean.class.getName());

    @Inject IndexStoreServiceWorker worker;

    @ConsumeEvent(NotificationService.NOTIFICATION_TOPIC)
    public void onMessage(Event event) {
        LOGGER.log(Level.INFO, "Index Store listener event received");
        worker.submit(event.getEventType(), event.getSourceId());
    }
}

