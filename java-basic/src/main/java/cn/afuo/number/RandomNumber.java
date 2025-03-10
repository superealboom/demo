package cn.afuo.number;


import java.math.BigInteger;
import java.util.Random;


/**
 * 生成随机数
 */
public class RandomNumber {


    public static void main(String[] args) {
        String random = getRandom(11);
        System.out.println(random);
    }

    private static String getRandom(int count) {
        Random random = new Random();
        BigInteger number = BigInteger.ZERO;

        // 生成11位的随机数，每一位都在1-9之间
        for (int i = 0; i < count; i++) {
            int digit = random.nextInt(9) + 1;
            number = number.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
        }

        return number.toString();
    }

}
