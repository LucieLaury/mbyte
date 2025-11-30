package fr.jayblanc.mbyte.store.metrics;

import io.quarkus.arc.Lock;
import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class MetricsServiceBean implements MetricsService {

    private static final Logger LOGGER = Logger.getLogger(MetricsService.class.getName());

    private static final Map<String, Long> metrics = new HashMap<>();
    private static final Map<String, Long> latestMetrics = new HashMap<>();

    public MetricsServiceBean() {
    }

    @Override
    public Map<String, Long> listMetrics() {
        LOGGER.log(Level.FINE, "List all metrics");
        return metrics;
    }

    @Override
    public Long getMetric(String key) {
        LOGGER.log(Level.FINE, "get metric for key: " + key);
        return metrics.getOrDefault(key, 0L);
    }

    @Override
    public Map<String, Long> listLatestMetrics() {
        LOGGER.log(Level.FINE, "List latest metrics");
        return latestMetrics;
    }

    @Override
    public Long getLatestMetric(String key) {
        LOGGER.log(Level.FINE, "get latest metric for key: " + key);
        return latestMetrics.getOrDefault(key, 0L);
    }

    @Override
    @Lock
    public void incMetric(String key) {
        LOGGER.log(Level.FINE, "increment metric for key: " + key);
        metrics.put(key, metrics.getOrDefault(key, 0L) + 1);
        latestMetrics.put(key, latestMetrics.getOrDefault(key, 0L) + 1);
    }

    @Override
    @Lock
    public void decMetric(String key) {
        LOGGER.log(Level.FINE, "decrement metric for key: " + key);
        metrics.put(key, Math.min(metrics.getOrDefault(key, 0L) - 1, 0L));
        latestMetrics.put(key, Math.min(latestMetrics.getOrDefault(key, 0L) - 1, 0L));
    }

    @Scheduled(every="5m")
    public void razLatestMetrics() {
        LOGGER.log(Level.INFO, "reset latest metrics");
        latestMetrics.replaceAll((k, v) -> 0L);
    }
}

