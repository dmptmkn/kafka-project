package org.example.kafkaproducer.configuration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.kafkacore.Alert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public Properties kafkaProducerProperties() {
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        producerProperties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        producerProperties.put("schema.registry.url", "http://localhost:8081");

        return producerProperties;
    }

    @Bean
    public KafkaProducer<Long, Alert> kafkaProducer(Properties kafkaProducerProperties) {
        return new KafkaProducer<>(kafkaProducerProperties);
    }

}
