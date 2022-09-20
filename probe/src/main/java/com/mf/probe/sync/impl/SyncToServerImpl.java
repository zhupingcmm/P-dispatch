package com.mf.probe.sync.impl;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.constants.Constants;
import com.mf.probe.sync.SyncToServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncToServerImpl<T extends ProbeInfo> implements SyncToServer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void syncToServer(ProbeInfo probeInfo) {
        ListenableFuture<SendResult<String, T>> future =  kafkaTemplate.send(Constants.PROBE_INFO_TOPIC, (T) probeInfo);
        try {
           SendResult<String, T> result = future.get(2, TimeUnit.SECONDS);
           log.info("Send probe info to server: {}", result.toString());
        } catch (InterruptedException | RuntimeException | ExecutionException | TimeoutException e) {
            log.error(e.getMessage());
        }
    }
}
