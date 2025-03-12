package cn.afuo.javabasic.character;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * GBK 和 UTF-8 编码转换
 */
public class Charset {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String utf8Str = "清山";
        String utf82Utf8 = new String(utf8Str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        System.out.println(utf82Utf8);

        String utf82Gbk = new String(utf8Str.getBytes(StandardCharsets.UTF_8), "gbk");
        System.out.println(utf82Gbk);

        String gbkStr = "娓呭北";
        String gbk2Gbk = new String(gbkStr.getBytes("gbk"), "gbk");
        System.out.println(gbk2Gbk);

        String gbk2Utf8 = new String(gbkStr.getBytes("gbk"), StandardCharsets.UTF_8);
        System.out.println(gbk2Utf8);
    }

}
