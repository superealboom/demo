package com.example.loadbalance.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * 所有用户集体，从application.properties中获取
 * 类似从数据库取数据
 * @author: tianci
 * @date: 2022/8/17 15:43
 */

@Data
@Component
@ConfigurationProperties(prefix="person")
public class Person {

    private String name;

    private List<User> userList;
}
