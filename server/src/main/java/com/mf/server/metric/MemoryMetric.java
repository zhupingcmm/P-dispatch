package com.mf.server.metric;

import com.mf.server.calculate.impl.AbstractCalculator;
import org.springframework.stereotype.Component;

@Component("memory")
public class MemoryMetric extends AbstractCalculator {
    @Override
    public long calculate() {
        return 0;
    }
}
