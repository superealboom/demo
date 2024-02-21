package com.superealboom.demo.string;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @description:
 * @author: tianci
 * @date: 2023/4/20 09:53
 */
public class SplitDemo {

    public static void main(String[] args) throws IOException {

        Properties sourceCfg = new Properties();
        sourceCfg.load(Files.newInputStream(Paths.get("/Users/tianci/IdeaProjects/batchserver/etc/cddfile.conf")));
        String cols_delimiter = sourceCfg.getProperty("cols_delimiter");
        System.out.println(cols_delimiter);

        String str = "1534140985818042369|     |54|auto@111.com|2022-06-07 19:51:29|2022-06-07 19:51:29";
        String[] split = str.split(cols_delimiter);
        System.out.println(split.length);
    }
}
