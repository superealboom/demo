package com.superealboom.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class ProducerByFileDemo {

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

        File file = new File("/Users/tianci/Documents/test.dsv");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))){
            String line;
            while((line = reader.readLine()) !=null) {
                System.out.println(line);
                sendSync("topic01","key-1", line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
