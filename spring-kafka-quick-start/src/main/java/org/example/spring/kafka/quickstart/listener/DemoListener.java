package org.example.spring.kafka.quickstart.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Song.Z
 */
@Slf4j
@Component
public class DemoListener {
    @KafkaListener(groupId = "$Default", topics = "demo")
    public void receive(ConsumerRecord<String, String> record) {
        log.info("Receive data[{}] at offset[{}]", record.value(), record.offset());
    }
}
