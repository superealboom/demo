package cn.afuo.webtool.middleware;

import cn.afuo.webtool.constant.RocketMQConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RocketMQProducerServiceTest {

    @Autowired
    private RocketMQProducerService rocketMQProducerService;


    @Test
    public void sendMessage() throws Exception {
        String bodyMessage = "hello, rocketMQ!";
        rocketMQProducerService.sendMessage(RocketMQConstants.ORDER_TOPIC, RocketMQConstants.ORDER_TAG, bodyMessage);
    }

}