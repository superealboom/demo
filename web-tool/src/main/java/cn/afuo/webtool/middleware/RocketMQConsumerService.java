package cn.afuo.webtool.middleware;

import cn.afuo.webtool.constant.RocketMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * rocketmq消费者工具类
 */
@Slf4j
@Component
public class RocketMQConsumerService {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    private DefaultMQPushConsumer consumer;

    @PostConstruct
    public void init() throws Exception {
        consumer = new DefaultMQPushConsumer(RocketMQConstants.ORDER_PRODUCER_GROUP);
        consumer.setNamesrvAddr(nameServer);
        consumer.subscribe(RocketMQConstants.ORDER_TOPIC, RocketMQConstants.ORDER_TAG);
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (MessageExt message : messages) {
                log.info("rocketmq消费消息，topic:{},tag:{},body:{}", RocketMQConstants.ORDER_TOPIC, RocketMQConstants.ORDER_TAG, new String(message.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }

    @PreDestroy
    public void destroy() {
        if (consumer != null) {
            consumer.shutdown();
            log.info("RocketMQ Consumer shutdown.");
        }
    }

}
