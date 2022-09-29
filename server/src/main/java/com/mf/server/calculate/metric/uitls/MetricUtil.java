package com.mf.server.calculate.metric.uitls;

import com.mf.server.common.Metric;
import com.mf.server.common.ProbeItem;

public class MetricUtil {

    public static Metric getMetric(ProbeItem probeItem, String metricName) {
        return probeItem.getMetrics()
                .stream()
                .filter(x -> x.getName().equals(metricName))
                .findFirst()
                .orElse(null);
    }
}
