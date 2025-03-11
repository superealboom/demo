package cn.afuo.javabasic.lambda;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * lambda排序
 */
public class SortDemo {


    public static void main(String[] args) {
        List<String> nameList = Arrays.asList("a","d","c","e","b");

        // 顺序
        nameList.sort(Comparator.naturalOrder());
        String naturalOrder = StringUtils.join(nameList, ",");
        System.out.println("naturalOrder顺序：" + naturalOrder);

        // 顺序
        nameList.sort(String::compareTo);
        String compareToSort = StringUtils.join(nameList, ",");
        System.out.println("compareToSort顺序：" + compareToSort);

        // 倒序
        nameList.sort(Comparator.reverseOrder());
        String reverseOrder = StringUtils.join(nameList, ",");
        System.out.println("reverseOrder倒序：" + reverseOrder);
    }

}
