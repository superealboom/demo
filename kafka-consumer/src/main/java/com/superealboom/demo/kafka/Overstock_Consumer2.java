package com.superealboom.demo.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author tianci
 * @description 查看kafka积压情况
 * @date 2022/4/19 16:03
 */
public class Overstock_Consumer2 extends Thread {

    // static Consumer<String, String> consumer;
    static AdminClient adminClient;
    static List<String> topicNameList = new ArrayList<>();
    static String lock = "lock";

    String clientId;

    public Overstock_Consumer2(String clientId) {
        this.clientId = clientId;
    }

    public static void main(String[] args) throws Exception {
        topicNameList.add("topic01");
        topicNameList.add("topic02");
        topicNameList.add("topic03");
        topicNameList.add("topic04");

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
        // consumer = new KafkaConsumer<>(properties);
        adminClient = AdminClient.create(properties);

        new Overstock_Consumer2("client1").start();
        new Overstock_Consumer2("client2").start();
    }

    @Override
    public void run() {
        final Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-1");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-kafka-1" + clientId);
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
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        Map<String, TopicDescription> stringTopicDescriptionMap = null;
        try {
            stringTopicDescriptionMap = adminClient.describeTopics(topicNameList).all().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (TopicDescription topicDescription: stringTopicDescriptionMap.values()) {
            List<TopicPartitionInfo> partitions = topicDescription.partitions();
            for (TopicPartitionInfo topicPartitionInfo : partitions) {
                topicPartitionList.add(new TopicPartition(topicDescription.name(), topicPartitionInfo.partition()));
            }
        }

        Map<TopicPartition, Long> beginningOffsetMap = consumer.beginningOffsets(topicPartitionList);
        Map<TopicPartition, Long> endOffsetMap = consumer.endOffsets(topicPartitionList);
        // Map<TopicPartition, Long> beginningOffsetMap = null;
        // Map<TopicPartition, Long> endOffsetMap = null;
        // synchronized (lock) {
        //     beginningOffsetMap = consumer.beginningOffsets(topicPartitionList);
        //     endOffsetMap = consumer.endOffsets(topicPartitionList);
        // }

        for (TopicPartition topicPartition :topicPartitionList) {
            String topicName = topicPartition.topic();
            int partition = topicPartition.partition();
            long beginningOffset = beginningOffsetMap.get(topicPartition);
            long endOffset = endOffsetMap.get(topicPartition);
            System.out.println("threadName:" + threadName + " - topic:" + topicName + " - partition:" + partition + " - beginningOffset" + beginningOffset + " - endOffset" + endOffset);
        }

        ListConsumerGroupOffsetsResult consumerGroupOffsetsResult = adminClient.listConsumerGroupOffsets("kafka-1");
        Map<TopicPartition, OffsetAndMetadata> consumerGroupOffsetsMap = null;
        try {
            consumerGroupOffsetsMap = consumerGroupOffsetsResult.partitionsToOffsetAndMetadata().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<TopicPartition, org.apache.kafka.clients.consumer.OffsetAndMetadata> entry : consumerGroupOffsetsMap.entrySet()) {
            String topic = entry.getKey().topic();
            int partition = entry.getKey().partition();
            long offset = entry.getValue().offset();
            System.out.println("threadName:" + threadName + ",topic:" + topic + "," + "partition:" + partition + "currentOffset:" + offset);
        }
    }
}
