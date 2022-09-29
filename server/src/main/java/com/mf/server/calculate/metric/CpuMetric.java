package com.mf.server.calculate.metric;

import com.mf.server.calculate.Calculator;
import com.mf.server.calculate.metric.uitls.MetricUtil;
import com.mf.server.common.Metric;
import com.mf.server.common.ProbeItem;
import org.springframework.stereotype.Component;

@Component("cpu")
public class CpuMetric<T extends ProbeItem> implements Calculator<T> {

    @Override
    public void calculate(T t) {
        // 这里 score 计算的逻辑 可以自定义， 这里只是实例代码，可以更加自己需求和场景定义
        Metric metric = MetricUtil.getMetric(t, "cpu");
        long preScore = t.getScore();

        if (metric.getUsage() <= 0.4) {
            preScore = preScore + 10;
        } else if (0.4 < metric.getUsage() && metric.getUsage() <= 0.6) {
            preScore = preScore + 5;
        } else if (0.8 < metric.getUsage() && metric.getUsage() <= 0.8){
            preScore = preScore + 1;
        }
        t.setScore(preScore);
    }
}
