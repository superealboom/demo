## 项目所有示例
### java-basic
> java 基础示例项目
1. 检测字符串中是否全部为中文。`cn.afuo.character.ChineseCharacter`
2. Unicode与字符互转。`cn.afuo.character.Unicode`
### web-tool
> web 项目工具
1. 自定义注解 FunctionLog，加在方法上，打印方法入参/出参。`cn.afuo.webtool.annotation.FunctionLog` 和 `cn.afuo.webtool.aspect.FunctionLogAspect`
2. 自定义注解 NoRepeatSubmitAspect，加载方法上，防止重复提交。`cn.afuo.webtool.annotation.NoRepeatSubmitAspect` 和 `cn.afuo.webtool.aspect.NoRepeatSubmitAspect`
3. 分布式锁 Redisson 引入。`cn.afuo.webtool.config.RedissonConfig`
4. Redis模板封装。`cn.afuo.webtool.config.RedisTemplateConfig` 和 `cn.afuo.webtool.util.RedisUtil`
5. Web通用后端返回封装对象。`cn.afuo.webtool.domain.Result` 和 `cn.afuo.webtool.enums.ResultEnum`
6. Date工具包。`cn.afuo.webtool.util.DateUtil`
7. JackSon工具包。`cn.afuo.webtool.util.JsonUtil`