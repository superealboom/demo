package com.superealboom.demo.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/5/11 14:06
 */
public class StringDemo {

    @Test
    public void xiegang() throws Exception {
        String line = "";
        File file = new File("/Users/tianci/newtouch/test.conf");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))){
            while((line = reader.readLine()) !=null) {
                System.out.println(line);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String str1 = "start\\\u001Bend";
        System.out.println(str1);

        String str2 = str1.replace("\\","\\\\");
        System.out.println(str2);

        String str3 = str2.replace("\\\\","\\");
        System.out.println(str3);
    }

    @Test
    public void huiche() {
        byte[] a ={0x0a};
        String SPLIT_A = new String(a);
        byte[] b ={0x1b};
        String SPLIT_B = new String(b);
        byte[] d ={0x0d};
        String SPLIT_D = new String(d);

        String result = "人事ogg23\r换行\n回车";
        String data = result;
        data = result.replace(SPLIT_A,"").replace(SPLIT_D, "")
                .replace("\n","").replace("\r","");
        System.out.println(data);
    }

    @Test
    public void contains() {
        String str1 = "properties";
        String str2 = "rti";
        if (StringUtils.contains(str1, str2)) {
            System.out.println(true);
        }
    }

    @Test
    public void length0() {
        String str = "";
        if (!str.startsWith("'")) {
            System.out.println("123");
        } else {
            System.out.println("321");
        }
    }
}
