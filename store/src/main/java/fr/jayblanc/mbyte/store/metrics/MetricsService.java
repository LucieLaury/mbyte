package fr.jayblanc.mbyte.store.metrics;

import java.util.Map;

public interface MetricsService {

    Map<String, Long> listMetrics();

    Map<String, Long> listLatestMetrics();

    Long getMetric(String key);

    Long getLatestMetric(String key);

    void incMetric(String key);

    void decMetric(String key);

}
