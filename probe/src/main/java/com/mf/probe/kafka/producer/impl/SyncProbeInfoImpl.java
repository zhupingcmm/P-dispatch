package com.mf.probe.kafka.producer.impl;

import com.alibaba.fastjson.JSON;
import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.constants.Constants;
import com.mf.dispatch.common.exception.DispatchException;
import com.mf.probe.kafka.producer.SyncProbeInfo;
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
public class SyncProbeInfoImpl <T extends ProbeInfo> implements SyncProbeInfo<T> {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void syncToServer(T t) {
        ListenableFuture<SendResult<String, String>> future =  kafkaTemplate.send(Constants.PROBE_INFO_TOPIC, JSON.toJSONString(t));
        try {
            SendResult<String, String> result = future.get(2, TimeUnit.SECONDS);
            log.debug("Success send probe info to server: {}", result.toString());
        } catch (InterruptedException | RuntimeException | ExecutionException | TimeoutException e) {
            log.error("Send probe info and get a error {}", e.getMessage());
            throw new DispatchException(ResponseEnum.SEND_DATA_ERROR);
        }
    }
}
