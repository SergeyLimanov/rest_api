package com.limanov.rest.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my_dog_api", groupId = "my_consumer")
    public void listen(String message) {
        System.out.println(message);
    }
}
