package cn.afuo.javabasic.number;

import java.math.BigDecimal;

/**
 * double 类型
 */
public class DoubleNumber {


    public static void main(String[] args) {
        double doubleValue1 = 0.1;
        double doubleValue2 = 0.1;
        System.out.println("0.1：" + doubleCompare(doubleValue1, doubleValue2));

        BigDecimal bigDecimal = new BigDecimal("0.1");
        double bigDecimalDouble = bigDecimal.doubleValue();
        System.out.println("0.1：" + doubleAndBigDecimalCompare(bigDecimalDouble, bigDecimal));

        BigDecimal bigDecimal1 = new BigDecimal("0.1234567890123456789");
        double bigDecimalDouble1 = bigDecimal.doubleValue();
        System.out.println("0.1234567890123456789：" + doubleAndBigDecimalCompare(bigDecimalDouble1, bigDecimal1));

        BigDecimal bigDecimal2 = new BigDecimal("0.12345678");
        double bigDecimalDouble2 = bigDecimal2.doubleValue();
        System.out.println("0.12345678：" + doubleAndBigDecimalCompare(bigDecimalDouble2, bigDecimal2));

    }


    private static boolean doubleCompare(double d1, double d2) {
        return d1 == d2;
    }


    private static boolean doubleAndBigDecimalCompare(double d, BigDecimal b) {
        return b.equals(BigDecimal.valueOf(d));
    }

}
