package com.limanov.rest.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public NewTopic newTopic() {
        return new NewTopic("my_dog_api", 1 , (short) 1);
    }
}
