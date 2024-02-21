package com.superealboom.demo.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.time.Duration;
import java.util.*;

/**
 * @author tianci
 * @description 查看kafka积压情况
 * @date 2022/4/19 16:03
 */
public class Overstock_Consumer {

    @Test
    public void look() {
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
        // 也可以用  consumer.listTopics() 列举
        List<String> topicNameList = Arrays.asList("test","topic01","topic02","topic03","topic04");

        long startTime = System.currentTimeMillis();
        for (String topicName : topicNameList) {
            List<TopicPartition> topicPartitionList = new ArrayList<>();
            Collection<PartitionInfo> partitionInfos = consumer.partitionsFor(topicName);
            partitionInfos.forEach(
                    partitionInfo -> topicPartitionList.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()))
            );
            Map<TopicPartition, Long> beginningOffsetMap = consumer.beginningOffsets(topicPartitionList);
            Map<TopicPartition, Long> endOffsetMap = consumer.endOffsets(topicPartitionList);
            for (TopicPartition topicPartition :topicPartitionList) {
                System.out.print("topicName:" + topicPartition.topic() + ",");
                System.out.print("partition:" + topicPartition.partition() + "  ");
                System.out.print("beginningOffset:" + beginningOffsetMap.get(topicPartition) + ",");
                OffsetAndMetadata committed = consumer.committed(topicPartition);
                if (committed != null)
                    System.out.print("currentOffset:" + committed.offset() + ",");
                else
                    System.out.print("currentOffset:0,");
                System.out.print("endOffset:" + endOffsetMap.get(topicPartition) + ",");
                if (committed != null)
                    System.out.println("lag:" + (endOffsetMap.get(topicPartition) - committed.offset()));
                else
                    System.out.println("lag:" + (endOffsetMap.get(topicPartition)));
                //lag = endOffset - currentOffset;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }
}
