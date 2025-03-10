package cn.afuo.webtool.annotation;


import java.lang.annotation.*;

/**
 * 方法日志
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionLog {

    String desc() default "";

}
