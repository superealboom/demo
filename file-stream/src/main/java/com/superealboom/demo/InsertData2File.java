package com.superealboom.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 验证 PrintWriter 缓存大小，验证得16384字节，16KB
 * @author: tianci
 * @date: 2022/4/21 10:31
 */
public class InsertData2File {

    @Test
    public void start() throws Exception {
        String str = "12345678123456781234567812345678"; //32字节
        String fileName = "C:\\Users\\afuo\\Desktop\\dataFile\\data.txt";
        List<String> dataList = new ArrayList<>();
        for (int i=0;i<511;i++) {
            dataList.add(str);
        }
        String str2 = "supermansupermansupermansuperman"; //32字节 - 到这里一共16384
        dataList.add(str2);
        String str3 = "################################"; //32字节 - 到这里一共16384 + 32
        dataList.add(str3);
        insertFile(fileName, dataList);
    }


    private void insertFile(String fileName, List<String> dataList) throws Exception {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        // 给定大小输出缓冲区
        // PrintWriter out = new PrintWriter(new BufferedWriter(fw,size));
        for (String str : dataList) {
            pw.print(str);
        }
        pw.flush();
        fw.flush();
        pw.close();
        fw.close();
    }

}
