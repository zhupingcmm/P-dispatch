package com.mf.server.metric;

import com.mf.server.calculate.impl.AbstractCalculator;
import org.springframework.stereotype.Component;

@Component("cpu")
public class CpuMetric extends AbstractCalculator {
    @Override
    public long calculate() {
        return 0;
    }
}
