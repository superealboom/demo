package cn.afuo.webtool.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交
 * ElementType枚举意义————
 * ElementType.CONSTRUCTOR 用在构造器
 * ElementType.FIELD 用于描述域-属性上
 * ElementType.METHOD 用在方法上
 * ElementType.TYPE 用在类或接口上
 * ElementType.PACKAGE 用于描述包
 * RetentionPolicy枚举意义————
 * RetentionPolicy.SOURCE 保留到源码上
 * RetentionPolicy.CLASS 保留到字节码上
 * RetentionPolicy.RUNTIME 保留到虚拟机运行时（最多，可通过反射获取）
 * Documented 将此注解包含在 javadoc 中
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 指定时间内不可重复提交，单位毫秒
     */
    long timeout() default 5000;

    /**
     * 指定参数
     */
    String appointParamName() default "";

}
