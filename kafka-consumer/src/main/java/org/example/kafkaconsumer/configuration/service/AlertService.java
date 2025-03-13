package org.example.kafkaconsumer.configuration.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.kafkacore.Alert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final KafkaConsumer<Long, Alert> kafkaConsumer;

    public AlertService(KafkaConsumer<Long, Alert> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public void consumeAlerts() {
        kafkaConsumer.subscribe(List.of("alerts"));

        while (true) {
            ConsumerRecords<Long, Alert> polledRecords = kafkaConsumer.poll(250);

            for (ConsumerRecord<Long, Alert> record : polledRecords) {
                System.out.println(record.key() + ": " + record.value());
            }
        }
    }
}
