package cn.afuo.webtool.aspect;

import cn.afuo.webtool.annotation.FunctionLog;
import cn.afuo.webtool.annotation.NoRepeatSubmit;
import cn.afuo.webtool.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AOP切面+自定义注解
 */
@RequestMapping("aspect")
@RestController
public class AspectController {

    /**
     * 测试打印日志和防重提交
     */
    @FunctionLog(desc = "你好，姓名，年龄，性别")
    @NoRepeatSubmit(appointParamName = "name")
    @GetMapping("hello")
    public Result<?> hello(String name, Integer age, String gender) {
        if (StringUtils.isBlank(name) || age == null || StringUtils.isBlank(gender)) {
            return Result.fail().message("参数不能为空");
        }
        String str = String.format("hello! %s, 年龄：%s，性别：%s", name, age, gender);
        return Result.success(str);
    }


    /**
     * 环绕通知执行前
     * 玩游戏前
     * url=http://localhost:8010/webtool/aspect/game, method=GET, ip=0:0:0:0:0:0:0:1, class_method=cn.afuo.webtool.business.controller.DemoController.game, args=[Ljava.lang.Object;@1a292401
     * 开始玩儿【DNF】游戏
     * 玩游戏后且没有异常 or 玩游戏后且有异常
     * 玩游戏后
     * 环绕通知执行后(抛异常不走这个方法)
     */
    @GetMapping("game")
    public void game(String gameName) {
        System.out.println("开始玩儿【" + gameName + "】游戏");
    }
}
