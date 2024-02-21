package com.superealboom.demo.pattern;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @description: 正则表达式
 * @author: tianci
 * @date: 2022/5/6 20:50
 */
public class PatternDemo {

    /*
     * @description: 匹配规定日期格式
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/4/24 9:51
     */
    @Test
    public void date() {
        String date = "^\\d{4}-\\d{2}-\\d{2}";
        String str1 = "1234-12-12";
        if ((Pattern.compile(date).matcher(str1).find())) {
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }

    
    /* 
     * @description: 匹配正整数
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/4/24 9:51
     */
    @Test
    public void number() {
        String number = "^[1-9]\\d*$";
        String str1 = "3.0";
        if ((Pattern.compile(number).matcher(str1).find())) {
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }

    /* 
     * @description: 只去除尾部空格 或者首部空格
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/4/24 9:50
     */
    @Test
    public void string() {
        String text = "   a   b   c\n123   ";
        String text1 = text.replaceAll("\\s*+$|\n", "");
        String text2 = text.replaceAll("^+\\s*|\n", "");
        System.out.println(text);
        System.out.println(text1);
        System.out.println(text2);
    }


    @Test
    public void complex() {
        String data = "123一二三abcABC娓呭北�\u001B";
        String PATTERN_MESSY_CODE = "[^a-zA-Z0-9\033 \t�\\p{Punct}\\p{InCJK Unified Ideographs}，。、《》？；‘’：“”【】｛｝￥·（）×÷]";
        String result = data.replaceAll(PATTERN_MESSY_CODE, "-");
        System.out.println(result);
    }
}
