package com.mf.server.kafka;

import com.mf.dispatch.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveProbeInfo {

    @KafkaListener(topics = Constants.PROBE_INFO_TOPIC)
    public void onHeartbeat(ConsumerRecord<?,?> record) {
        log.info("简单消费 topic: {} partition {}, value: {}", record.topic(), record.partition(), record.value());
    }
}
