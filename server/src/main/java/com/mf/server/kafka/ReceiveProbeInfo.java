package com.mf.server.kafka;

import com.alibaba.fastjson.JSON;
import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.constants.Constants;
import com.mf.server.service.ProbeInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveProbeInfo <T extends ProbeInfo>{

    private final ProbeInfoService probeInfoService;

    @KafkaListener(topics = Constants.PROBE_INFO_TOPIC)
    public void onHeartbeat(ConsumerRecord<String,T> record) {
        ProbeInfo probeInfo = JSON.parseObject(String.valueOf(record.value()), ProbeInfo.class);
        probeInfoService.updateProbeInfo(probeInfo);
        log.debug("topic: {} partition {}, value: {}", record.topic(), record.partition(), record.value());
    }
}
