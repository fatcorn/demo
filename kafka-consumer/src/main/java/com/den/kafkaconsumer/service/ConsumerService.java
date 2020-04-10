package com.den.kafkaconsumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Component;

/**
 * @author fatKarin
 * @date 2020/4/10 13:37
 */
@Component
public class ConsumerService {

    @KafkaListener(topics = "test",groupId = "test-group")
    public void onOrderSubmitted(ConsumerRecord<String,String> record) throws InterruptedException {
        System.out.println("key:" + record.key());
        System.out.println("value:" + record.value());
        Thread.sleep(1000);
    }
}
