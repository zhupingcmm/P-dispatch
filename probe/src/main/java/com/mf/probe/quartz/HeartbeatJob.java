package com.mf.probe.quartz;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.probe.sync.SyncToServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeartbeatJob  extends QuartzJobBean {

    private final SyncToServer<ProbeInfo> syncToServer;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ProbeInfo probeInfo = ProbeInfo.builder().cpu(0.8).probeId(1).build();
        log.info("Start heartbeat to server with info: {}", probeInfo.toString());
//        syncToServer.syncToServer(probeInfo);
    }
}
