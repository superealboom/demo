package com.superealboom.demo.charset;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @description: GBK乱码
 * @author: tianci
 * @date: 2022/4/29 18:54
 */
public class CharsetDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "清山";
        // byte[] b = s.getBytes();//编码
        String s3 = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        System.out.println(s3);
        String sa = new String(s.getBytes(StandardCharsets.UTF_8), "gbk");//解码
        System.out.println(sa);

        String str = "娓呭北";
        System.out.println(new String(str.getBytes("gbk"), "gbk"));
        System.out.println(new String(str.getBytes("gbk")));

    }

}
