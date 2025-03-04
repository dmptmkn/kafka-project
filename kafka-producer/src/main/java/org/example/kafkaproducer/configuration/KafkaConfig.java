package org.example.kafkaproducer.configuration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.example.kafkacore.Alert;
import org.example.kafkaproducer.partitioner.AlertLevelPartitioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public Properties kafkaProducerProperties() {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092,localhost:9096,localhost:9097");
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.LongSerializer");
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.KafkaAvroSerializer");
        producerProperties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
                AlertLevelPartitioner.class.getName());
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, "3");
        producerProperties.put("schema.registry.url", "http://localhost:8081");

        return producerProperties;
    }

    @Bean
    public KafkaProducer<Long, Alert> kafkaProducer(Properties kafkaProducerProperties) {
        return new KafkaProducer<>(kafkaProducerProperties);
    }

}
