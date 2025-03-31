package cn.afuo.webtool.middleware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZookeeperServiceTest {

    @Autowired
    private ZookeeperService zookeeperService;

    @Test
    public void createNode() throws Exception {
        zookeeperService.createNode("/test", "Hello, ZooKeeper!");
    }

    @Test
    public void readNode() throws Exception {
        zookeeperService.readNode("/test");
    }

    @Test
    public void deleteNode() throws Exception {
        zookeeperService.deleteNode("/test");
    }



}