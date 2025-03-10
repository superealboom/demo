package cn.afuo.webtool.business.controller;


import cn.afuo.webtool.annotation.FunctionLog;
import cn.afuo.webtool.annotation.NoRepeatSubmit;
import cn.afuo.webtool.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("demo")
@RestController
public class DemoController {


    @FunctionLog(desc = "访问 demo 中的 hello")
    @NoRepeatSubmit(appointParamName = "name")
    @GetMapping("hello")
    public Result<?> hello(String name, Integer age, String gender) {
        if (StringUtils.isBlank(name) || age == null || StringUtils.isBlank(gender)) {
            return Result.fail().message("参数不能为空");
        }
        String str = String.format("hello! %s, 年龄：%s，性别：%s", name, age, gender);
        return Result.success(str);
    }


}
