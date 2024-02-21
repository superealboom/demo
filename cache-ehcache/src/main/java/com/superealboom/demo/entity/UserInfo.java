package com.superealboom.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: tianci
 * @date: 2022/4/24 16:54
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
}
