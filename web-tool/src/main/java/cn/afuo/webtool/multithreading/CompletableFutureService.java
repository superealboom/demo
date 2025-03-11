package cn.afuo.webtool.multithreading;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CompletableFutureService {

    /**
     * 使用线程池+CompletableFuture到达异步执行方法汇总到主线程
     */
    public void doAsyncMethod(int quantity) {
        ExecutorService executorService = Executors.newFixedThreadPool(quantity);
        try {
            List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
            for (int i=0;i<quantity;i++) {
                final int index = i;
                CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                    log.info("线程{}:{}开始", Thread.currentThread().getName(), index);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("线程{}:{}结束", Thread.currentThread().getName(), index);
                }, executorService);
                completableFutureList.add(completableFuture);
            }
            CompletableFuture<Void> allCompletableFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
            allCompletableFuture.get();
        } catch (Exception e) {
            log.error("CompletableFutureService.doAsyncMethod异常", e);
        } finally {
            /* 平缓地关闭ExecutorService，不再接受新的任务提交，
            但它不会中断已经在执行的任务。它会等待所有已提交的任务执行完毕（包括那些已经在工作队列中排队的任务），然后关闭服务。
            这意味着调用此方法后，执行器将不再处理任何新提交的任务，但已开始的任务会继续执行直到完成。*/
            executorService.shutdown();

            /* 在指定时间内执行器成功终止，此方法返回 true；如果等待超时则返回 false，但这不会阻止剩余任务继续执行至完成。
            因此，这个方法主要用于控制主线程的等待行为，而不是干预或终止线程池中任务的执行。 */
            // executorService.awaitTermination(1, TimeUnit.MINUTES);
        }
    }


    /**
     * 使用supplyAsync代替runAsync例子
     * supplyAsync 方法期望一个计算能够产生一个结果
     * runAsync 方法用于执行一个不返回任何值的任务
     * 如果计算结果对后续操作是必要的，那么应当使用 supplyAsync；反之，如果计算只是执行一些操作而不关心结果，那么 runAsync 更为合适。
     */
    public void doAsyncMethodAboutSupply(int quantity) {
        ExecutorService executorService = Executors.newFixedThreadPool(quantity);
        List<CompletableFuture<Boolean>> completableFutureList = new ArrayList<>();
        for (int i=0;i<quantity;i++) {
            final int index = i;
            CompletableFuture<Boolean> completableFuture = CompletableFuture.supplyAsync(() -> index % 2 == 0, executorService);
            completableFuture.thenAccept(result -> log.info("thenAccept - result:{}", result));
            completableFutureList.add(completableFuture);
        }
        CompletableFuture<Void> allCompletableFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        try {
            allCompletableFuture.get();
            completableFutureList.forEach(future -> {
                Boolean result = future.join();
                if (Boolean.TRUE.equals(result)) {
                    log.info("join - result:{}", true);
                } else {
                    log.info("join - result:{}", result);
                }
            });
            executorService.shutdown();
            boolean result = executorService.awaitTermination(1, TimeUnit.MINUTES);
            log.info("awaitTermination - result:{}", result);
        } catch (Exception e) {
            log.error("CompletableFutureService.doAsyncMethodAboutSupply异常", e);
        }
    }
}
