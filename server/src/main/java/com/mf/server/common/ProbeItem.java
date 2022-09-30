package com.mf.server.common;

import com.mf.dispatch.common.base.BaseBean;
import com.mf.dispatch.common.base.Task;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProbeItem extends BaseBean {
    private long probeId;

    private long customerId;

    private long score;

    private List<Task> taskQueue;

    private List<Metric> metrics;
}
