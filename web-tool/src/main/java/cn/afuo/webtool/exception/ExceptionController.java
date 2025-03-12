package cn.afuo.webtool.exception;

import cn.afuo.webtool.domain.Result;
import cn.afuo.webtool.enums.ResultEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("exception")
@RestController
public class ExceptionController {

    /**
     * 自定义异常
     */
    @GetMapping("code")
    public Result<?> code(Integer exceptionCode) {
        if (ResultEnum.FAIL.getCode() == exceptionCode) {
            throw new WebToolException("测试自定义异常");
        }
        return Result.success();
    }
}
