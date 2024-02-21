package com.example.loadbalance.entity;

import lombok.Data;

/**
 * @description: 用户信息实体
 * @author: tianci
 * @date: 2022/5/22 11:13
 */
@Data
public class User {

    // 用于数据库主键
    private long id;
    // 用户名，不能重复
    private String username;
    // 用户密码
    private String password;
    // 用户角色(0:管理员,1:普通用户)
    private Integer role;
}
