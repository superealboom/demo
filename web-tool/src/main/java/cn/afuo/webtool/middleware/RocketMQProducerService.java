package cn.afuo.webtool.middleware;

import cn.afuo.webtool.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * rocketmq生产者工具类
 */
@Slf4j
@Component
public class RocketMQProducerService {

    @Autowired
    @Qualifier("orderMQProducer")
    private DefaultMQProducer orderMQProducer;

    public void sendMessage(String topic, String tag, String body) throws Exception {
        Message message = new Message(topic, tag, body.getBytes());
        message.setKeys(NumberUtil.generateUniqueKey());
        orderMQProducer.send(message);
        log.info("rocketmq生产消息，topic:{},tag:{},body:{}", topic, tag, body);
    }

}
