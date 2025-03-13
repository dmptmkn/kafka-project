package org.example.kafkaproducer.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.kafkacore.Alert;
import org.example.kafkacore.AlertStatus;
import org.example.kafkaproducer.callback.AlertCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/alert")

public class AlertController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaProducer<Long, Alert> kafkaProducer;

    @Autowired
    public AlertController(KafkaProducer<Long, Alert> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public void sendAlert() {
        Alert alert = new Alert(12345L, Instant.now().toEpochMilli(), AlertStatus.Warning);

        ProducerRecord<Long, Alert> producerRecord = new ProducerRecord<>(
                "alerts", alert.getSensorId(), alert
        );

        kafkaProducer.send(producerRecord, new AlertCallback());
    }
}
