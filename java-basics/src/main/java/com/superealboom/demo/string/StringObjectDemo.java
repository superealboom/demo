package com.superealboom.demo.string;

import org.junit.Test;

/**
 * @description:
 * @author: tianci
 * @date: 2023/4/25 10:48
 */
public class StringObjectDemo {

    @Test
    public void main() {
        System.out.println(42 == 42.0);// true

        System.out.println("--------------");

        String new1 = new String("ab"); // a 为一个引用
        String new2 = new String("ab"); // b为另一个引用,对象的内容一样
        String constant1 = "ab"; // 放在常量池中
        String constant2 = "ab"; // 从常量池中查找
        System.out.println(constant1 == constant2);// true
        System.out.println(new1 == new2);// false
        System.out.println(new1.equals(new2));// true

        System.out.println("--------------");

        System.out.println(new1.equals(constant1));//true
        System.out.println(constant1.equals(new1));//ture
        System.out.println(new1 == constant1);//false
    }

    @Test
    public void intDemo() {
        Integer i1 = 40;//缓存中对象
        Integer i2 = new Integer(40);
        System.out.println(i1==i2);
        System.out.println(i1.equals(i2));

        Integer i3 = 440;
        Integer i4 = 440;
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));

        Integer i5 = 44;
        Integer i6 = 44;
        System.out.println(i5 == i6);
    }
}
