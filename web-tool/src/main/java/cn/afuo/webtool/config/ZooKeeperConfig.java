package cn.afuo.webtool.config;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper配置
 */
@Configuration
public class ZooKeeperConfig {


    @Value("${zookeeper.address}")
    private String zkAddress;

    @Value("${zookeeper.sessionTimeout}")
    private int sessionTimeout;

    @Bean(destroyMethod = "close")
    public ZooKeeper zooKeeper() throws IOException, InterruptedException {
        CountDownLatch connectedSignal = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(zkAddress, sessionTimeout, event -> {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectedSignal.countDown();
            }
        });
        connectedSignal.await();
        return zk;
    }
}