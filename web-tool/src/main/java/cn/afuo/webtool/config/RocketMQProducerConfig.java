package cn.afuo.webtool.config;

import cn.afuo.webtool.constant.RocketMQConstants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rocketmq生产者配置
 */
@Configuration
public class RocketMQProducerConfig {


    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Bean(name = "orderMQProducer")
    public DefaultMQProducer orderMQProducer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.ORDER_PRODUCER_GROUP);
        producer.setNamesrvAddr(nameServer);
        // 增加发送消息超时时间 单位：毫秒
        producer.setSendMsgTimeout(10000);
        // 设置最大消息大小
        producer.setMaxMessageSize(1024 * 1024);
        producer.start();
        return producer;
    }
}
