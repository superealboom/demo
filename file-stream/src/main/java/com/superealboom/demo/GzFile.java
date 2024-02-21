package com.superealboom.demo;

import com.huawei.shade.org.apache.http.HttpEntity;
import com.huawei.shade.org.apache.http.client.methods.CloseableHttpResponse;
import com.huawei.shade.org.apache.http.client.methods.HttpGet;
import com.huawei.shade.org.apache.http.impl.client.CloseableHttpClient;
import com.huawei.shade.org.apache.http.impl.client.HttpClients;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2023/4/19 09:52
 */
public class GzFile {

    public static void main(String[] args) throws Exception {
        // List<String> strings = sendGetToGzip("/Users/tianci/Documents/test_user.tar.gz");
        printGz("/Users/tianci/Documents/user2.csv.gz");
    }


    /**
     * @description: 读取gz文件
     * @param: [fileName]
     * @return: void
     * @author: tianci
     * @date: 2023/4/20 09:33
     */
    public static void printGz(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(Files.newInputStream(Paths.get(fileName)))))){
            String line;
            while((line = reader.readLine()) !=null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @description: 读取tar.gz文件
     * @param: [fileName]
     * @return: void
     * @author: tianci
     * @date: 2023/4/20 09:33
     */
    public static void printTarGz(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(Files.newInputStream(Paths.get(fileName)))))){
            String line;
            while((line = reader.readLine()) !=null) {
                if (line.lastIndexOf("\u0000") > -1) {
                    String substring = line.substring(line.lastIndexOf("\u0000")+1);
                    if (StringUtils.isNotBlank(substring)) {
                        System.out.println(substring);
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @description: http读取
     * @param: [url]
     * @return: java.util.List<java.lang.String>
     * @author: tianci
     * @date: 2023/4/20 09:33
     */
    public static List<String> sendGetToGzip(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        List<String> lines = new ArrayList();
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream in = new GZIPInputStream(entity.getContent());
                Scanner sc = new Scanner(in);
                while (sc.hasNextLine()) {
                    lines.add(sc.nextLine());
                }
                in.close();
                System.out.println(lines.toString());
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}
