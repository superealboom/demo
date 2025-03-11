package cn.afuo.webtool.multithreading;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class FutureService {


    /**
     * 使用线程池+Future到达异步执行方法汇总到主线程
     */
    public void doAsyncMethod(int quantity) {
        // 创建固定大小为quantity的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(quantity);
        log.info("创建线程池({})成功", quantity);
        try {
            // 存储每个异步方法的Future对象
            List<Future<Void>> futureList = new ArrayList<>();
            for (int i=0;i<quantity;i++) {
                final int index = i; // 为匿名内部类捕获索引值
                Future<Void> future = executorService.submit(() -> {
                    log.info("线程{}:{}开始", Thread.currentThread().getName(), index);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("线程{}:{}结束", Thread.currentThread().getName(), index);
                    return null;
                });
                futureList.add(future);
            }
            // 等待所有任务完成
            for (Future<Void> future : futureList) {
                future.get();
            }
        } catch (Exception e) {
            log.error("FutureService.doAsyncMethod异常", e);
        } finally {
            executorService.shutdown();
        }
    }

}
