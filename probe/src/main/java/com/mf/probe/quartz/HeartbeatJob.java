package com.mf.probe.quartz;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.base.os.SystemHardwareInfo;
import com.mf.probe.kafka.producer.SyncProbeInfo;
import com.mf.probe.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeartbeatJob  extends QuartzJobBean {

    private final SyncProbeInfo<ProbeInfo> syncProbeInfo;

    private final TaskService<Task> taskService;

    @Value("${probe.id}")
    private long probeId;

    @Value("${probe.customer-id}")
    private long customerId;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)  {
        // 从 tb_task_queue 获取 probe中所有的 task 列表
        List<Task> tasks =  taskService.getTasks();
        // 获取 当前probe节点的系统信息 cpu、jvm、memory、os
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        ProbeInfo probeInfo = ProbeInfo.builder()
                .cpu(systemHardwareInfo.getCpu())
                .jvm(systemHardwareInfo.getJvm())
                .osInfo(systemHardwareInfo.getOsInfo())
                .memory(systemHardwareInfo.getMemory())
                .customerId(customerId)
                .probeId(probeId)
                .status(0)
                .taskQueue(tasks)
                .build();
        // 向server发出心跳，并且带上probe的信息
        syncProbeInfo.syncToServer(probeInfo);
    }
}
