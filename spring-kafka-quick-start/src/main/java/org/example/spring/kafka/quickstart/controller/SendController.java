package org.example.spring.kafka.quickstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.kafka.quickstart.data.constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author Song.Z
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class SendController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{batch}/{num}")
    public String send(@PathVariable("batch") int batch, @PathVariable("num") int num)  {

        List<ListenableFuture<SendResult<String,String>>> listenableFutures = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            String data = String.format("value #%d in batch %d", i, batch);
            ListenableFuture<SendResult<String,String>> listenableFuture
                    = kafkaTemplate.send(constants.TOPIC, data);
            listenableFutures.add(listenableFuture);
        }

        for (ListenableFuture<SendResult<String, String>> listenableFuture : listenableFutures) {
            try {
                SendResult<String, String> sendResult = listenableFuture.get();
                sendResult.getProducerRecord().value();
                log.info("发送[{}]成功. 偏移量：{}",
                        sendResult.getProducerRecord().value(),
                        sendResult.getRecordMetadata().offset());
            }
            catch (ExecutionException | InterruptedException e) {
                log.error("发送异常：{}", e.getMessage() );
            }
        }
        return String.format("Send %d data complate!", num);
    }
}
