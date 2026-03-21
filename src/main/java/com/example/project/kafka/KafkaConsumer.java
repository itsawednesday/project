package com.example.project.kafka;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    //Kör docker-compose up första gång för att starta docker image

    @KafkaListener(topics = "my_topic", groupId = "my_group_id")
    public void getMessage(String message) {

        System.out.println(message);

    }
}
