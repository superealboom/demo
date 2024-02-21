package org.example;

import cn.hutool.crypto.symmetric.AES;

public class Aes {

    public static String sessionKey = "Q3IHJSQ17E8UT2CL";

    public static void main(String[] args) {

        AES aes = new AES("ECB", "PKCS5Padding", sessionKey.getBytes());

        // 加密为16进制表示
        String encryptHex = aes.encryptBase64("1234");
        System.out.println(encryptHex);
        // 解密
        String decryptStr = aes.decryptStr("2CPTprjn8yYfIuRqCA/rJw==");
        System.out.println(decryptStr);
    }
}