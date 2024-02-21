package com.superealboom.demo.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @description: 数字处理
 * @author: tianci
 * @date: 2022/5/6 20:49
 */
public class DoubleDemo {

    /**
     * @description: 整数四舍五入
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/6 20:49
     */
    @Test
    public void smallNumber() {
        double double1 = 123.556;
        System.out.println(Math.round(double1));
    }

    /**
     * @description: 四舍五入法
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/6 20:49
     */
    @Test
    public void smallNumber2() {
        double a = 0.088065185546875;
        double b = (double)(Math.round(a*100))/100;
        System.out.println(b);
    }

    /**
     * @description: 四舍五入法
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/6 20:48
     */
    @Test
    public void smallNumber3() {
        double a = 0.048065185546875;
        BigDecimal b = new BigDecimal(a);
        double c = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(c);
    }

    /**
     * @description: double转decimal
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/6 20:47
     */
    @Test
    public void double2Decimal() {
        String num = "123.1";
        double double1 = Double.parseDouble(num);
        System.out.println(BigDecimal.valueOf(double1));
    }

    /**
     * @description: 去掉科学计数法
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/6 20:47
     */
    @Test
    public void formatDouble() {
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
