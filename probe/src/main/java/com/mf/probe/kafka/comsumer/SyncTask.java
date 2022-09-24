package com.mf.probe.kafka.comsumer;

import com.alibaba.fastjson.JSON;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.constants.Constants;
import com.mf.dispatch.common.exception.DispatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SyncTask <T extends Task>{


    @Value("probe.id")
    private long probeId;

    @KafkaListener(topics = Constants.PROBE_TASK_TOPIC)
    public void onTask(ConsumerRecord<String,T> record, Consumer consumer) {

        try {
            Task task = JSON.parseObject(String.valueOf(record.value()), Task.class);
            log.info("Receive task from server,  topic: {} partition {}, value: {}, offset {}", record.topic(), record.partition(), record.value(), record.offset());
            // 判断task 是否属于该 probe 如果不属于就直接忽略，不做任何的操作
            if (task.getProbeId() != probeId) {
                log.warn("this task is not belong to this probe");
                return;
            }




        } finally {
            consumer.commitAsync(Collections.singletonMap(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1)), (Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) -> {
                if (exception != null) {
                    log.error(exception.getMessage());
                    throw new DispatchException(exception);
                }

                log.debug("Probe commit the offset: {}", offsets.toString());
            });
        }

    }

}
