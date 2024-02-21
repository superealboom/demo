package com.superealboom.demo.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/6/7 16:55
 */
public class Time {

    public static void main(String[] args) {
        System.out.println(isDateVail("1999-06-07 16:32:00"));
    }

    private static Boolean isDateVail(String date) {
        //用于指定 日期/时间 模式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boolean flag = true;
        try {
            //Java 8 新添API 用于解析日期和时间
            LocalDateTime.parse(date, dtf);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
