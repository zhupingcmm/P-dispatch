package com.mf.server.common;

import com.mf.dispatch.common.base.BaseBean;
import com.mf.dispatch.common.base.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProbeItem extends BaseBean {
    private long probeId;

    private CalcResult calcResult;

    private List<Task> taskQueue = new ArrayList<>();

    private List<Metric> metrics = new ArrayList<>();
}
