package org.example.kafkaproducer.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertCallback implements Callback {

    public static final Logger logger = LoggerFactory.getLogger(AlertCallback.class);

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            logger.error("Kafka error: {}", exception.getMessage());
        } else {
            logger.info("Kafka info: offset = {}, topic = {}, partition = {}, timestamp = {}",
                    metadata.offset(), metadata.topic(),
                    metadata.partition(), metadata.timestamp()
            );
        }
    }
}
