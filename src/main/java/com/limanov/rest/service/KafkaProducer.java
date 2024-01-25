package com.limanov.rest.service;

import com.limanov.rest.dto.DogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public final String POSITION_TOPIC_NAME = "my_dog_api";

    public void produce(DogDto dto) {
        kafkaTemplate.send(POSITION_TOPIC_NAME, dto);
    }
}
