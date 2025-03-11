package cn.afuo.webtool.validation;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 */
@Validated
public class ValidatedMain {


    public static void main(String[] args) {
        interceptSize(20);
    }


    private static void interceptSize(@Min(1) @Max(10) int size) {
        System.out.println(size);
    }
}
