package com.mf.probe.quartz;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.base.os.SystemHardwareInfo;
import com.mf.probe.sync.SyncToServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeartbeatJob  extends QuartzJobBean {

    private final SyncToServer<ProbeInfo> syncToServer;

    @Value("${probe.id}")
    private long probeId;

    @Value("${probe.customer-id}")
    private long customerId;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Task task = Task.builder()
                .jobName("test")
                .status(0)
                .probeId(probeId)
                .build();
        LinkedList<Task> tasks = new LinkedList<>();
        tasks.add(task);
        ProbeInfo probeInfo = ProbeInfo.builder()
                .systemInfo(new SystemHardwareInfo())
                .customerId(customerId)
                .probeId(probeId)
                .status(0)
                .taskQueue(tasks)
                .build();
        log.info("Start heartbeat to server with info: {}", probeInfo.toString());
        // 向server发出心跳，并且带上probe的信息
        syncToServer.syncToServer(probeInfo);
    }
}
