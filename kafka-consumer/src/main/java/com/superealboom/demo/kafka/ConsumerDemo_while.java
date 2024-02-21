package com.superealboom.demo.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @description: while
 * @author: tianci
 * @date: 2022/5/2 20:38
 */
public class ConsumerDemo_while {

    public static void main(String[] args) throws Exception {
        final Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-1");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-kafka-1");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, "33554432");
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "30720");
        properties.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "3000");
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1048576");
        properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "104857600");
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "1200000");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1000000");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000");
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RangeAssignor");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        List<String> topicNameList = Arrays.asList("topic04");
        consumer.subscribe(topicNameList);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
            }
            consumer.commitSync();
        }
    }

}
