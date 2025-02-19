package org.example.kafkaconsumer.configuration;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.kafkacore.Alert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public Properties kafkaConsumerProperties() {
        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", "localhost:9094");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        consumerProperties.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        consumerProperties.put("group.id", "kafka_demo_consumer");
        consumerProperties.put("enable.auto.commit", "true");
        consumerProperties.put("auto.commit.interval.ms", "1000");
        consumerProperties.put("schema.registry.url", "http://localhost:8081");

        return consumerProperties;
    }

    @Bean
    public KafkaConsumer<Long, Alert> kafkaConsumer(Properties kafkaConsumerProperties) {
        return new KafkaConsumer<>(kafkaConsumerProperties);
    }
}
