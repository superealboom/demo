package com.superealboom.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class CuratorConfig {

    private CuratorFramework zkClient;
    private final String PATH_ZOOKEEPER = "/consumers";

    @Test
    public void demo() throws Exception {
        setZkClient();
        System.out.println(existsPath("test","kafka-1",1));
        // setOffset("test","kafka-1",1,0L);
        // System.out.println(existsPath("test","kafka-1",1));
        // System.out.println(getOffset("test","kafka-1",1));
    }
    
    private void setZkClient() {
        if (zkClient == null) {
            int sessionTimeout = 60000;// session超时时间
            int connectionTimeout = 60000;// 连接超时时间
            int baseSleepTimeMs = 5000;// 重试间隔时间
            int retryCount = 5;// 重试次数
            String ZOOKEEPER_HOST = "127.0.0.1:2181/kafka";
            zkClient = CuratorFrameworkFactory
                    .builder()
                    .connectString(ZOOKEEPER_HOST)
                    .sessionTimeoutMs(sessionTimeout)
                    .connectionTimeoutMs(connectionTimeout)
                    .retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, retryCount))
                    // .namespace("")
                    .build();
            zkClient.start();
            zkClient.getState();
        }
    }

    /**
     *  @Date: 2021/12/10 14:36
     *  @Description: 设置offset
     */
    public void setOffset(String topic, String groupId, Integer partition, Long offset) throws Exception {
        String path = PATH_ZOOKEEPER + "/" + groupId  + "/" + topic + "/" + partition;
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path, offset.toString().getBytes());
        } else {
            zkClient.setData().forPath(path, offset.toString().getBytes());
        }
    }

    /**
     *  @Date: 2021/12/16 11:03
     *  @Description: 拿到offset
     */
    public long getOffset(String topic, String groupId, Integer partition) throws Exception {
        String path = PATH_ZOOKEEPER + "/" + groupId + "/"  + topic + "/" + partition;
        byte[] bytes = zkClient.getData().forPath(path);
        return Long.parseLong(new String(bytes));
    }

    /**
     *  @Date: 2021/12/10 15:49
     *  @Description: 判断zookeeper是否存在路径
     */
    public boolean existsPath(String topic, String groupId, Integer partition) throws Exception {
        setZkClient();
        String path = PATH_ZOOKEEPER + "/" + groupId + "/"  + topic + "/" + partition;
        Stat stat = zkClient.checkExists().forPath(path);
        return stat != null;
    }
}
