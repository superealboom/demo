package cn.afuo.webtool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * zookeeper工具类
 */
@Slf4j
@Component
public class ZkUtil {

    private final ZooKeeper zk;

    public ZkUtil(ZooKeeper zk) {
        this.zk = zk;
    }

    /**
     * 创建节点
     */
    public String createNode(String path, String data) throws KeeperException, InterruptedException {
        String createdPath = zk.create(path, data.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("Node created: {}", createdPath);
        return createdPath;
    }

    /**
     * 读取节点数据
     */
    public String readNode(String path) throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zk.getData(path, false, stat);
        String result = new String(data);
        log.info("Node data: {}", result);
        return result;
    }

    /**
     * 删除节点
     */
    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zk.delete(path, -1);
        log.info("Node deleted: {}", path);
    }

}