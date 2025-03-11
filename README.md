## 项目所有示例
### java-basic
> java 基础示例项目
1. 检测字符串中是否全部为中文。

   `cn.afuo.javabasic.character.ChineseCharacter`

2. Unicode与字符互转。

   `cn.afuo.javabasic.character.Unicode`

3. Double 和 BigDecimal 比较。

   `cn.afuo.javabasic.number.DoubleNumber`

4. Lombok 使用详情。

   `cn.afuo.javabasic.lombok.LombokDetail`

5. 文件流 txt 操作。

   `cn.afuo.javabasic.file.TxtReader`

6. Lambda 使用：排序、静态接口、创建线程。

   `cn.afuo.javabasic.lambda`

7. 实现 Serializable 序列化意义。

   `cn.afuo.javabasic.serializable`

8. 尝试 jdk8 特性 predicate 断言使用。

   `cn.afuo.javabasic.predicate`
### web-tool
> web 项目工具
1. 自定义注解 FunctionLog，加在方法上，打印方法入参/出参。

   `cn.afuo.webtool.annotation.FunctionLog` 

   `cn.afuo.webtool.aspect.FunctionLogAspect`

2. 自定义注解 NoRepeatSubmitAspect，加在方法上，防止重复提交。

   `cn.afuo.webtool.annotation.NoRepeatSubmitAspect` 

   `cn.afuo.webtool.aspect.NoRepeatSubmitAspect`

3. 分布式锁 Redisson 引入。

   `cn.afuo.webtool.config.RedissonConfig`

4. Redis模板封装。

   `cn.afuo.webtool.config.RedisTemplateConfig`

    `cn.afuo.webtool.util.RedisUtil`

5. Web通用后端返回封装对象。

   `cn.afuo.webtool.domain.Result`

    `cn.afuo.webtool.enums.ResultEnum`

6. Date工具包。

   `cn.afuo.webtool.util.DateUtil`

7. JackSon工具包。

   `cn.afuo.webtool.util.JsonUtil`

8. Aspect 注解下各个注解的执行顺序 doBefore、doAfter、doAfterReturning、doAfterThrowing、around。

   `cn.afuo.webtool.aspect.GameAspect`