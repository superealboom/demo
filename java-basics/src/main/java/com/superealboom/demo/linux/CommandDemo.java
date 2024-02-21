package com.superealboom.demo.linux;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @description: linux命令
 * @author: tianci
 * @date: 2022/5/7 11:14
 */
public class CommandDemo {

    public static void main(String[] args) {
        run_command("mv /Users/tianci/Documents/t2.md /Users/tianci/Documents/t.md");
        // run_command("cat /Users/tianci/Documents/t2.md");
    }


    private static void run_command(String cmdStr) {
        String line;
        try {
            ProcessBuilder pb;
            if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")) {
                pb = new ProcessBuilder("cmd", "/c", cmdStr);
            } else {
                pb = new ProcessBuilder("sh", "-c", cmdStr);
            }
            pb.redirectErrorStream(true);
            Process pro = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            while ((line = br.readLine()) != null) {
                // 输出日志到大屏幕上
                System.out.println(line);
            }
            br.close();
            pro.waitFor();
            int cmd_ret = pro.exitValue();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

}
