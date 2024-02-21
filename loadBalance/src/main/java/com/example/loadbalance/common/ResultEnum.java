package com.example.loadbalance.common;

import lombok.Getter;


/**
 *  @Author: tianci
 *  @Date: 2022/4/7 13:37
 *  @Description: 状态码
 */
@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),

    ERROR(500, "失败")

    ;

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}