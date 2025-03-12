package cn.afuo.webtool.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 验证入参合法
 */
@Validated
@Component
public class ValidatedService {


    public void interceptSize(@Min(1) @Max(10) int size) {
        System.out.println(size);
    }


}
