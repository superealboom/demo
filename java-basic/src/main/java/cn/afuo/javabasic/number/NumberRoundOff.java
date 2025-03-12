package cn.afuo.javabasic.number;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 数字四舍五入
 */
public class NumberRoundOff {


    public static void main(String[] args) {
        integerRoundOff();
        doubleRoundOff();
        decimalRoundOff();
        formatDouble();
    }

    /**
     * 整数四舍五入
     */
    private static void integerRoundOff() {
        double double1 = 123.556;
        System.out.println(Math.round(double1));
    }

    /**
     * 小数四舍五入-Double
     */
    private static void doubleRoundOff() {
        double a = 0.088065185546875;
        double b = (double)(Math.round(a*100))/100;
        System.out.println(b);
    }

    /**
     * 小数四舍五入-BigDecimal
     */
    private static void decimalRoundOff() {
        double a = 0.048065185546875;
        BigDecimal b = new BigDecimal(a);
        double c = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(c);
    }

    /**
     * 去掉科学计数法
     */
    private static void formatDouble() {
        double d = 0.048065185546875;
        NumberFormat nf = NumberFormat.getInstance();
        //设置保留多少位小数
        nf.setMaximumFractionDigits(6);
        // 取消科学计数法
        nf.setGroupingUsed(false);
        //返回结果
        System.out.println(nf.format(d));
    }
}
