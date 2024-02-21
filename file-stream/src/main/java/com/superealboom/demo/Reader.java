package com.superealboom.demo;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @description: 读取文件
 * @author: tianci
 * @date: 2022/5/9 16:39
 */
public class Reader {

    public static void main(String[] args) throws Exception {
        System.out.println(getResult("/Users/tianci/Desktop/scott.emp.txt"));
    }

    public static String getResult(String filePath) {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))){
            String line;
            while((line = reader.readLine()) !=null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
