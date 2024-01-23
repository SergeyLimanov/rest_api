package com.limanov.rest.service;

import com.limanov.rest.dto.DogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "my_dog_api", groupId = "my_consumer",
            properties = {"spring.json.value.default.type=com.limanov.rest.dto.DogDto"})
    public void listen(DogDto dogDto) {
        Long id = dogDto.getId();
        String name = dogDto.getName();
       log.info(String.format("You have received dog from app with id: %s and name %s", id, name));
    }
}
