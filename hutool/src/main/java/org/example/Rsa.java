package org.example;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/10/31 11:06
 */
public class Rsa {



    public static void main(String[] args) {
        // RSA rsa = new RSA();
        // System.out.println(rsa.getPrivateKeyBase64());
        // System.out.println(rsa.getPublicKeyBase64());

        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALaUn0maPKDwYJqjualsS5bhIOaS0ieBwIZfjFMHkpfAZB5yn9bwSygi+qJMLga8WI+uZPfiTS5J7VxnvE+VS/FGmGOdCdPxaDACn9QwpA0RZ6qyBmHhNXMap+ssvYjXKF+m+Mfghiqwv/OIN7HmvgoKe5IW4e3uoxV6b0487bjjAgMBAAECgYEAgxukaoEUKmmSpVkOmIfIm/bb3lbuDCqTaqqwJGtarBgejPK4rursvUyo9+SGZJXZbovHu21JCQVTKjjfs7I8zjOEW8D5d2LjzLy1frBdgM6dywdhQECqdmbOnoFLZJjRr6pe6wdcqHm472AkRCANyW+TpWBN0Qivb8R3cZckIhkCQQD0wcq0sbP+jTn14ha1uYOXFAhCZduQU4E+wOES0tK1NgNFstLtY16KUyzo5txmibABEuDR30taqNTuot1/AEsXAkEAvverY9o/Pz0/oAKdt9yvvYl2dzKZqP9H7NurLvxMewxa0urVXA87SDbiPVCw7BSkCBvAvvUgV6Bc/xqSOc7wFQJAN0iqk6Pr5xxZMivOO1/a1D4nG3BMZs4lzFnevNS32UyI8q5QrGQcj5v38edrcxG4mpV8CIuqRqEJ75kD9NLJIwJBAKOExWAiACgxVOq5cr1+HYlpTGQykrrPpJHfYww/g2mVoaua44kJjdR/Emg4UNZFslMzGG8qzD/cE+Vopi8T0CUCQQDIEYw2zizGE7halQOF5fazjj7etx7a7ShHwaA7aeuNqgNVUyyQNET/LoOziLjfvjXrQjLmuqbVmduiDF3XMRJp";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2lJ9Jmjyg8GCao7mpbEuW4SDmktIngcCGX4xTB5KXwGQecp/W8EsoIvqiTC4GvFiPrmT34k0uSe1cZ7xPlUvxRphjnQnT8WgwAp/UMKQNEWeqsgZh4TVzGqfrLL2I1yhfpvjH4IYqsL/ziDex5r4KCnuSFuHt7qMVem9OPO244wIDAQAB";

        RSA rsa = new RSA(privateKey, publicKey);
        //公钥加密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes(Aes.sessionKey, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        String encode = Base64.encode(encrypt);
        System.out.println(encode);

        //私钥解密
        byte[] decode = Base64.decode(encode);
        byte[] decrypt = rsa.decrypt(decode, KeyType.PrivateKey);
        System.out.println(new String(decrypt));
    }




}
