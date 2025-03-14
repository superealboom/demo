package cn.afuo.javabasic.file;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 操作 txt
 */
public class TxtReader {

    public static void main(String[] args) {
        String filePath = "/Users/tianci/VsCodeProjects/gitconfig.txt";
        getResult(filePath);
        getResultByUTF8(filePath);
    }



    private static void getResult(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void getResultByUTF8(String filePath) {
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
    }

}
