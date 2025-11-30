package fr.jayblanc.mbyte.store.metrics;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@MetricsSource
@Priority(20)
@Interceptor
public class MetricsInterceptor {

    private static final Logger LOGGER = Logger.getLogger(MetricsInterceptor.class.getName());

    @Inject MetricsService metrics;

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        LOGGER.log(Level.FINE, "Entering interceptor or method: " + ic.getTarget().toString(), ic.getMethod().getName());
        try {
            Object obj =  ic.proceed();
            if ( ic.getMethod().isAnnotationPresent(GenerateMetric.class) ) {
                LOGGER.log(Level.INFO, "Annotation metrics found, applying metrics");
                GenerateMetric annotation = ic.getMethod().getAnnotation(GenerateMetric.class);
                switch ( annotation.type() ) {
                    case INCREMENT: metrics.incMetric(annotation.key()); break;
                    case DECREMENT: metrics.decMetric(annotation.key()); break;
                }
            }
            return obj;
        } finally {
            LOGGER.log(Level.FINE, "Exiting interceptor or method: " + ic.getTarget().toString(), ic.getMethod().getName());
        }
    }
}
