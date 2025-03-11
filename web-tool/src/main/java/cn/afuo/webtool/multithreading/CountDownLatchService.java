package cn.afuo.webtool.multithreading;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CountDownLatchService {


    /**
     * 使用线程池+CountDownLatch到达异步执行方法汇总到主线程
     */
    public void doAsyncMethod(int quantity) {
        ExecutorService executorService = Executors.newFixedThreadPool(quantity);
        CountDownLatch countDownLatch = new CountDownLatch(quantity);

        for (int i=0;i<quantity;i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    log.info("线程{}:{}开始", Thread.currentThread().getName(), index);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("线程{}:{}结束", Thread.currentThread().getName(), index);
                } catch (Exception e) {
                    log.error("线程{}:{}执行异常", Thread.currentThread().getName(), index);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            executorService.shutdown();
            countDownLatch.await();
            /* 如果确保线程池中所有已提交的任务都完成执行，executor.awaitTermination() 可能是多余的
            保留 executor.awaitTermination() 仍然有一些好处：
                它可以作为一个“保险策略”，确保没有任何遗漏的任务仍在运行。
                如果在将来的某个时候，你决定向线程池添加更多的任务，那么这个方法将继续确保所有任务都完成执行。
            保留它不会对程序造成负面影响，反而可能增加代码的健壮性。
            */
            boolean awaitedResult = executorService.awaitTermination(1, TimeUnit.MINUTES);
            log.info("阻塞当前线程结果:{}", awaitedResult);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
