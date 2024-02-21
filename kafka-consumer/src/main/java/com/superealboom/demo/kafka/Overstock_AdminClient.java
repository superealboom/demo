package com.superealboom.demo.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author tianci
 * @description 查看kafka积压情况
 * @date 2022/4/19 16:53
 */
public class Overstock_AdminClient {

    @Test
    public void lookTopic() throws ExecutionException, InterruptedException {
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
        AdminClient adminClient = AdminClient.create(properties);
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 设置规定的topic，创建成TopicPartition，拿到begin和end的offset
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        List<String> topicList = new ArrayList<>();
        topicList.add("topic01");
        topicList.add("topic02");
        topicList.add("topic03");
        topicList.add("topic04");
        topicList.add("test");
        Map<String, TopicDescription> stringTopicDescriptionMap = adminClient.describeTopics(topicList).all().get();
        for (TopicDescription topicDescription: stringTopicDescriptionMap.values()) {
            List<TopicPartitionInfo> partitions = topicDescription.partitions();
            for (TopicPartitionInfo topicPartitionInfo : partitions) {
                topicPartitionList.add(new TopicPartition(topicDescription.name(), topicPartitionInfo.partition()));
            }
        }

        // 拿到消费者组下topic信息
        String groupId = "kafka-1";
        Map<TopicPartition, Long> beginningOffsetMap = consumer.beginningOffsets(topicPartitionList);
        Map<TopicPartition, Long> endOffsetMap = consumer.endOffsets(topicPartitionList);
        Map<String, List<String>> resultMap = new HashMap<>();
        ListConsumerGroupOffsetsResult consumerGroupOffsetsResult = adminClient.listConsumerGroupOffsets(groupId);
        Map<TopicPartition, org.apache.kafka.clients.consumer.OffsetAndMetadata> consumerGroupOffsetsMap = consumerGroupOffsetsResult.partitionsToOffsetAndMetadata().get();
        for (Map.Entry<TopicPartition, org.apache.kafka.clients.consumer.OffsetAndMetadata> entry : consumerGroupOffsetsMap.entrySet()) {
            String topic = entry.getKey().topic();
            int partition = entry.getKey().partition();
            long offset = entry.getValue().offset();

            String info = "topic:" + topic + "  ";
            info += "partition:" + partition + "  ";
            info += "beginOffset:" + beginningOffsetMap.get(entry.getKey()) + "  ";
            info += "currentOffset:" + offset + "  ";
            info += "endOffset:" + endOffsetMap.get(entry.getKey()) + "  ";
            info += "lag:" +  (endOffsetMap.get(entry.getKey()) - offset);

            if (!resultMap.containsKey(topic)) {
                List<String> infoList = new ArrayList<>();
                infoList.add(info);
                resultMap.put(topic, infoList);
            } else {
                resultMap.get(topic).add(info);
            }
        }

        for (String topic : resultMap.keySet()) {
            List<String> resultList = resultMap.get(topic);
            for (String str : resultList) {
                System.out.println(str);
            }
            System.out.println();
        }
    }


    @Test
    public void lookGroup() throws Exception {
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
        AdminClient adminClient = AdminClient.create(properties);
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 获取消费者组相关信息
        List<String> groupIdsOfTopic = new ArrayList<>();
        ListConsumerGroupsResult groupResult = adminClient.listConsumerGroups(new ListConsumerGroupsOptions());
        Collection<ConsumerGroupListing> list = groupResult.valid().get();
        List<String> groupIds = list.stream().map(ConsumerGroupListing::groupId).collect(Collectors.toList());
        DescribeConsumerGroupsResult consumerGroupsResult = adminClient.describeConsumerGroups(groupIds);
        Map<String, ConsumerGroupDescription> consumerGroupDescriptionMap = consumerGroupsResult.all().get();
        for (Map.Entry<String, ConsumerGroupDescription> entry : consumerGroupDescriptionMap.entrySet()) {
            ConsumerGroupDescription consumerGroupDescription = entry.getValue();
            String groupId = consumerGroupDescription.groupId();
            groupIdsOfTopic.add(groupId);
            for (MemberDescription memberDescription : consumerGroupDescription.members()) {
                String consumerId = memberDescription.consumerId();
                String host = memberDescription.host();
                System.out.println(consumerId);
                System.out.println(host);
                for (TopicPartition topicPartition : memberDescription.assignment().topicPartitions()) {
                    String unitTopic = topicPartition.topic();
                    Integer unitPartition = topicPartition.partition();
                    System.out.println(unitTopic);
                    System.out.println(unitPartition);
                }
            }
        }
    }
}
