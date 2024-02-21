package com.superealboom.demo.prop;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @description: 直接加载Properties文件
 * @author: tianci
 * @date: 2022/5/10 09:35
 */
public class PropDemo {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream( "/Users/tianci/Desktop/jaas_etl.conf"));
        System.out.println(properties.size());
    }
}
