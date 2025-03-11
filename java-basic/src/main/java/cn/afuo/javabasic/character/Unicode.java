package cn.afuo.javabasic.character;


/**
 * Unicode 取值
 */
public class Unicode {

    public static void main(String[] args) {
        String str = "㷋";
        String unicode = getUnicodeByStr(str);
        System.out.println(unicode);
        System.out.println(getStrByUnicode(unicode));
        System.out.println(getStrByUnicodeRange("3400", "4dbf"));
    }


    /**
     * 通过字符得到对应 unicode
     */
    private static String getUnicodeByStr(String str) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            // 取出每一个字符
            char c = str.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }


    /**
     * 通过 unicode 得到对应字符
     */
    private static String getStrByUnicode(String unicode) {
        StringBuilder sb = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int code = Integer.parseInt(hex[i], 16);
            sb.append((char) code);
        }
        return sb.toString();
    }


    /**
     * 通过 unicode 范围得到对应字符
     */
    private static String getStrByUnicodeRange(String startCodePoint, String endCodePoint) {
        StringBuilder sb = new StringBuilder();
        for (int i = Integer.parseInt(startCodePoint, 16); i <= Integer.parseInt(endCodePoint, 16); i++) {
            sb.append(Character.toChars(i));
        }
        return sb.toString();
    }
}
