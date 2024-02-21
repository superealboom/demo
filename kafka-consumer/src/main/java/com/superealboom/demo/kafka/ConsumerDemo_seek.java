package com.superealboom.demo.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * @description: seek
 * @author: tianci
 * @date: 2022/5/2 20:43
 */
public class ConsumerDemo_seek {

    public static void main(String[] args) {

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
        List<String> topicNameList = Collections.singletonList("test");
        consumer.subscribe(topicNameList);
        Set<TopicPartition> assignment = new HashSet<>();
        /*
            while poll无读数据的意义,这里poll的原因如下：
            seek只能重置消费者分配到的分区的消费位置,而分区的分配是在poll的调用过程中实现的
            也就是说，在执行seek之前需要先执行一次poll方法
        */
        while (assignment.size() == 0) {
            consumer.poll(Duration.ofMillis(100));
            assignment = consumer.assignment();
        }
        for (TopicPartition topicPartition : assignment) {
            System.out.println(topicPartition.topic() + "," + topicPartition.partition());
            consumer.seek(topicPartition, 26);
        }
        // 规定从设定的offset开始消费数据
        System.out.println("开始拉取数据");
        ConsumerRecords<String, String> consumerRecords = consumer.poll(5000);
        for (ConsumerRecord<String, String> record : consumerRecords) {
            System.out.println("offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
        }
        System.out.println(consumerRecords.count());
        consumer.commitSync();
        consumer.unsubscribe();
    }
}
