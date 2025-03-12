package cn.afuo.javabasic.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 执行 linux 命令
 */
public class CommandMain {

    public static void main(String[] args) {
        runCommand("mv /opt/mv1 /opt/mv2");
    }


    private static void runCommand(String cmdStr) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
