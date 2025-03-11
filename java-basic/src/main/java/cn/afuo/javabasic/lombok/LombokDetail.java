package cn.afuo.javabasic.lombok;

import lombok.Data;

/**
 * @Data: 组合了@Getter, @Setter, @ToString, @EqualsAndHashCode和@RequiredArgsConstructor
 */
@Data
public class LombokDetail {

    /*
     * @Builder坑: 初始化值为null、不可变final问题、私有字段问题、继承问题。
     * @NoArgsConstructor: 生成一个无参构造函数。
     * @AllArgsConstructor: 生成包含所有字段的构造函数。
     * @RequiredArgsConstructor: 生成包含所有final或@NonNull字段的构造函数。
     * 使用@AllArgsConstructor后@Value不能使用，再增加@NoArgsConstructor后可以使用，但是也有其他问题
     */

}
