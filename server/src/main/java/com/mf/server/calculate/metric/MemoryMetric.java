package com.mf.server.calculate.metric;

import com.mf.server.calculate.Calculator;
import com.mf.server.common.Metric;
import com.mf.server.common.ProbeItem;
import com.mf.server.calculate.metric.uitls.MetricUtil;
import org.springframework.stereotype.Component;

@Component("memory")
public class MemoryMetric <T extends ProbeItem> implements Calculator<T> {

    @Override
    public void calculate(ProbeItem probeItem) {
        // 这里 score 计算的逻辑 可以自定义， 这里只是实例代码，可以更加自己需求和场景定义
        Metric metric = MetricUtil.getMetric(probeItem, "memory");
        long preScore = probeItem.getScore();

        if (metric.getUsage() <= 0.3) {
            preScore = preScore + 10;
        } else if (0.3 < metric.getUsage() && metric.getUsage() <= 0.5) {
            preScore = preScore + 5;
        } else if (0.5 < metric.getUsage() && metric.getUsage() <= 0.7){
            preScore = preScore + 1;
        }
        probeItem.setScore(preScore);
    }
}
