package com.superealboom.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: time
 * @author: tianci
 * @date: 2022/6/7 15:39
 */
@Slf4j
public class ConsumerDemo_time {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        long fetchDataTime = df.parse("2023-07-19 11:30:05").getTime();
        restOffset(consumer, "topic01", fetchDataTime);

        // partition ={0}, time = {2022-07-19 11:06:46}, offset = {1727}
    }

    /**
     *
     * @Title restOffset
     * @Description
     * @param consumer      消费者
     * @param topic         主题
     * @param fetchDataTime 需要查找的位置点时间戳
     * @return void
     */
    private static void restOffset(Consumer<String, String> consumer, String topic, long fetchDataTime) {
        try {
            // 获取topic的partition信息
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
            List<TopicPartition> topicPartitions = new ArrayList<>();
            Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();

            for (PartitionInfo partitionInfo : partitionInfos) {
                topicPartitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
                timestampsToSearch.put(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()), fetchDataTime);
            }
            System.out.println("设置读取时间戳,{"+fetchDataTime+"}");
            consumer.assign(topicPartitions);

            // 获取每个partition一个小时之前的偏移量
            Map<TopicPartition, OffsetAndTimestamp> map = consumer.offsetsForTimes(timestampsToSearch);
            OffsetAndTimestamp offsetTimestamp = null;
            System.out.println(topic+",开始设置各分区初始偏移量...");

            for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : map.entrySet()) {
                // 如果设置的查询偏移量的时间点大于最大的offset记录时间，那么value就为空
                offsetTimestamp = entry.getValue();
                if (offsetTimestamp != null) {
                    int partition = entry.getKey().partition();
                    long timestamp = offsetTimestamp.timestamp();
                    long offset = offsetTimestamp.offset();
                    System.out.println("partition ={"+partition+"}, time = {"+ df.format(new Date(timestamp))+"}, offset = {"+offset+"}");
                    // 设置读取消息的偏移量
                    // consumer.seek(entry.getKey(), offset);
                }
            }
            System.out.println("{"+topic+"},设置各分区初始偏移量结束...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
