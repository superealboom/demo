package com.superealboom.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;


public class ProducerDemo {

    KafkaProducer<String, String> producer = null;

    @Test
    public void send() throws Exception {
        final Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-kafka-1");//client.id
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");//bootstrap.servers
        producerProps.put(ProducerConfig.ACKS_CONFIG, "-1");//acks
        producerProps.put(ProducerConfig.RETRIES_CONFIG, "30");//retries
        producerProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");//max.in.flight.requests.per.connection
        producerProps.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");//batch.size
        producerProps.put(ProducerConfig.LINGER_MS_CONFIG, "10");//linger.ms
        producerProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");//buffer.memory
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");//key.serializer
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");//value.serializer
        producerProps.put(ProducerConfig.SEND_BUFFER_CONFIG, "262144");

        producer = new KafkaProducer<>(producerProps);

        String str1 = "{\"current_ts\":\"2022-08-05T17:37:46.083000\",\"primary_keys\":\"DEPTNO\",\"pos\":3310089652101,\"deptcode\":\"tail_code\",\"op_type\":\"I\",\"after\":{\"ADRESS\":39.5,\"DEPTNO\":1,\"DNAME\":\"\"},\"op_ts\":\"2020-08-26 10:11:20.355760\",\"table\":\"SCOTT.DEPT2\"}";

        String str6 = "test";

        sendSync("topic01","key-1", str1);
    }


    public void sendSync(String topic, String key, String message) throws Exception {
        producer.send(new ProducerRecord<>(topic, key, message)).get();
    }

    public void sendAsync(String topic, String key, String message) {
        producer.send(new ProducerRecord<>(topic, key, message), (recordMetadata, e) -> {
            if (e != null){
                e.printStackTrace();
            }
        });
    }
}
