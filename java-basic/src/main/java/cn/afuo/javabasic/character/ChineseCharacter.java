package cn.afuo.javabasic.character;

/**
 * 判断字符是否为汉字
 */
public class ChineseCharacter {


    public static void main(String[] args) {
        String str = "我㷋";
        System.out.println(isChineseByRegular(str));
        System.out.println(isChineseByExpand(str));
    }


    /**
     * 通过正则表达式判断是否是汉字
     * 基本汉字的Unicode - CJK统一表意文字 - 无法匹配扩展汉字
     */
    private static boolean isChineseByRegular(String strName) {
        return strName.matches("^[\\u4e00-\\u9fa5]+$");
    }


    /**
     * 通过Unicode判断是否是汉字
     * 匹配所有汉字字符，包括基本汉字和扩展汉字
     */
    private static boolean isChineseByExpand(String strName) {
        return strName.matches("^[\\p{Script=Han}]+$");
    }



}
