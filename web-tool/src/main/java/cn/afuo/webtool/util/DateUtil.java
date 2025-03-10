package cn.afuo.webtool.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理时间
 */
public class DateUtil {

    private static final SimpleDateFormat format_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间
     */
    public static String now() {
        try {
            return format_yyyy_MM_dd_HH_mm_ss.format(new Date());
        } catch (Exception e) {
            return "";
        }
    }

}
