package com.mf.server.kafka;

import com.alibaba.fastjson.JSON;
import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.constants.Constants;
import com.mf.dispatch.common.exception.DispatchException;
import com.mf.server.service.ProbeInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveProbeInfo <T extends ProbeInfo>{

    private final ProbeInfoService probeInfoService;

    /**
     * 监听 kafka 中 probe info的 topic 信息
     * @param record
     */
    @KafkaListener(topics = Constants.PROBE_INFO_TOPIC)
    public void onHeartbeat(ConsumerRecord<String,T> record, Consumer consumer) {
        try {
            ProbeInfo probeInfo = JSON.parseObject(String.valueOf(record.value()), ProbeInfo.class);
            probeInfoService.updateProbeInfo(probeInfo);
            log.debug("topic: {} partition {}, value: {}, offset {}", record.topic(), record.partition(), record.value(), record.offset());
        } finally {
            consumer.commitAsync(Collections.singletonMap(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1))
                    , (Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) -> {
                if (exception != null) {
                    log.error(exception.getMessage());
                    throw new DispatchException(exception);
                }

                log.info(offsets.toString());


            });
        }
    }
}
