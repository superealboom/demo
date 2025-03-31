package cn.afuo.webtool.util;


import java.util.UUID;

public class NumberUtil {

    /**
     * 生成全局唯一的 message key。
     * @return 唯一的字符串作为 message key。
     */
    public static String generateUniqueKey() {
        // 使用UUID和当前时间戳组合生成唯一的key
        return UUID.randomUUID().toString() + "_" + System.currentTimeMillis();
    }
}
