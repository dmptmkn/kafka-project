package org.example.kafkaproducer.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.example.kafkacore.Alert;
import org.example.kafkacore.AlertStatus;

import java.util.Map;
import java.util.Random;

public class AlertLevelPartitioner implements Partitioner {

    @Override
    public int partition(String topic,
                         Object key,
                         byte[] keyBytes,
                         Object value,
                         byte[] valueBytes,
                         Cluster cluster) {
        Alert alert = (Alert) value;
        if (alert.getStatus() == AlertStatus.Critical) {
            return 0;
        } else {
            return new Random().nextInt(1, cluster.partitionCountForTopic(topic));
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }
}
