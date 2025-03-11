package cn.afuo.webtool.multithreading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultithreadingTest {

    @Autowired
    private CompletableFutureService completableFutureService;
    @Autowired
    private CountDownLatchService countDownLatchService;
    @Autowired
    private FutureService futureService;


    @Test
    public void doAsyncMethod() {
        int quantity = 5;
        // completableFutureService.doAsyncMethod(quantity);
        // completableFutureService.doAsyncMethodAboutSupply(5);
        // countDownLatchService.doAsyncMethod(quantity);
        futureService.doAsyncMethod(quantity);
    }

}