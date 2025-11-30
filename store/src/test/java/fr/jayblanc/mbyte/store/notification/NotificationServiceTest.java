package fr.jayblanc.mbyte.store.notification;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jerome Blanchard
 */
@QuarkusTest
public class NotificationServiceTest {

    private static final Logger LOGGER = Logger.getLogger(NotificationServiceTest.class.getName());

    @Inject NotificationService notification;

    @Inject UserTransaction userTx;

    @Test
    @Transactional
    public void simpleThrowEventTest() throws NotificationServiceException {
        LOGGER.log(Level.INFO, "Starting Simple Throw Event Test");
        notification.notify("data.create", "data.id.1");
        notification.notify("data.create", "data.id.2");
        notification.notify("data.delete", "data.id.2");
    }

    @Test
    public void txThrowEvent() throws Exception {
        LOGGER.log(Level.INFO, "Starting Transactionnal Throw Event");
        userTx.begin();
        notification.notify("data.create", "data.id.1");
        notification.notify("data.create", "data.id.2");
        userTx.commit();
        Thread.sleep(1000);
        userTx.begin();
        notification.notify("data.create", "data.id.3");
        userTx.rollback();
        Thread.sleep(1000);
        userTx.begin();
        notification.notify("data.create", "data.id.4");
        notification.notify("data.create", "data.id.5");
        notification.notify("data.create", "data.id.6");
        notification.notify("data.create", "data.id.7");
        userTx.commit();
    }


}
