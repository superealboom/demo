package cn.afuo.webtool.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ZkUtilTest {

    @Autowired
    private ZkUtil zkUtil;

    @Test
    public void createNode() throws Exception {
        zkUtil.createNode("/test", "Hello, ZooKeeper!");
    }

    @Test
    public void readNode() throws Exception {
        zkUtil.readNode("/test");
    }

    @Test
    public void deleteNode() throws Exception {
        zkUtil.deleteNode("/test");
    }



}