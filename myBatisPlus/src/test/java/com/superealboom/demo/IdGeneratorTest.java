package com.superealboom.demo;


import com.superealboom.demo.entity.User;
import com.superealboom.demo.mapper.UserMapper;
import com.superealboom.demo.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 主键生成测试类
 * @author: tianci
 * @date: 2022/4/20 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGeneratorTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setName("靓仔");
        user.setAge(18);
        userMapper.insert(user);

        testBatch();
    }

    /**
     * 批量插入
     */
    private void testBatch() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("靓仔" + i);
            user.setAge(18 + i);
            users.add(user);
        }
        boolean result = userService.saveBatch(users);
        Assertions.assertTrue(result);
    }


    @Test
    public void select() {
        long id = 1534140984660414466L;
        User user = userMapper.selectById(id);
        System.out.println(user.getName());
    }
}
