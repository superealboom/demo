package cn.afuo.javabasic.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作 txt
 */
public class TxtReader {

    public static void main(String[] args) {
        List<String> orderNoList = new ArrayList<>();
        String filePath = "/Users/tianci/Documents/文档-代码常用/test8.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                orderNoList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
