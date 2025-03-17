## java-basic

> java 基础示例项目
### 1. 检测字符串中是否全部为中文

`cn.afuo.javabasic.character.ChineseCharacter`

### 2. Unicode与字符互转

`cn.afuo.javabasic.character.Unicode`

### 3. Double和BigDecimal比较

`cn.afuo.javabasic.number.DoubleCompareBigDecimal`

### 4. Lombok使用详情

`cn.afuo.javabasic.lombok.LombokDetail`

### 5. 文件流 txt 操作

`cn.afuo.javabasic.file.TxtReader`

### 6. Lambda 使用：排序、静态接口、创建线程

`cn.afuo.javabasic.lambda`

### 7. 实现 Serializable 序列化意义

`cn.afuo.javabasic.serializable`

### 8. 尝试 jdk8 特性 predicate 断言使用

`cn.afuo.javabasic.predicate`

### 9. GBK和UTF-8解码编码

`cn.afuo.javabasic.character.Charset`

### 10. Linux命令执行

`cn.afuo.javabasic.command.CommandMain`

### 11. 反射的基本使用

`cn.afuo.javabasic.reflection`

## web-tool

> web 项目工具
### 1. 自定义注解FunctionLog，打印入参/出参

`cn.afuo.webtool.annotation.FunctionLog` 

`cn.afuo.webtool.aspect.FunctionLogAspect`

访问链接：`http://localhost:8010/webtool/aspect/hello?name=test&age=11&gender=1`

### 2. 自定义注解NoRepeatSubmitAspect，防止重复提交

`cn.afuo.webtool.annotation.NoRepeatSubmitAspect` 

`cn.afuo.webtool.aspect.NoRepeatSubmitAspect`

访问链接：`http://localhost:8010/webtool/aspect/hello?name=test&age=11&gender=1`

### 3. 分布式锁 Redisson 引入

`cn.afuo.webtool.config.RedissonConfig`

### 4. Redis模板封装

`cn.afuo.webtool.config.RedisTemplateConfig`

 `cn.afuo.webtool.util.RedisUtil`

### 5. Web通用后端返回封装对象

`cn.afuo.webtool.domain.Result`

 `cn.afuo.webtool.enums.ResultEnum`

### 6. Date工具包

`cn.afuo.webtool.util.DateUtil`

### 7. JackSon工具包

`cn.afuo.webtool.util.JsonUtil`

### 8. Aspect 注解下各个注解的执行顺序

`cn.afuo.webtool.aspect.GameAspect` 

访问链接：`http://localhost:8010/webtool/aspect/game`

### 9. 自定义异常和全局异常处理

`cn.afuo.webtool.exception.WebToolException`

`cn.afuo.webtool.exception.GlobalExceptionHandler`

访问链接：`http://localhost:8010/webtool/exception/code?exceptionCode=500`

### 10. 注解Validated使用：@Min、@Max 等

`cn.afuo.webtool.validation.ValidatedService`

`cn.afuo.webtool.validation.ValidatedServiceTest`

### 11. 多线程组件使用

ExecutorService、CompletableFuture、CountDownLatch、Future

`cn.afuo.webtool.multithreading.CompletableFutureService`

`cn.afuo.webtool.multithreading.CountDownLatchService`

`cn.afuo.webtool.multithreading.FutureService`

`cn.afuo.webtool.multithreading.MultithreadingTest`

### 12. 验证码图片

`cn.afuo.webtool.captcha`

访问链接：`http://localhost:8010/webtool/captcha/index`

![image-20250312161729545](https://afuo-blog.oss-cn-beijing.aliyuncs.com/demo/web-tool/image-20250312161729545.png)

### 13. 响应式编程 Flux

`cn.afuo.webtool.flux.FluxController`

访问链接：`http://localhost:8010/webtool/flux/index`

### 14. env动态加载数据到application配置

`cn.afuo.webtool.env.EnvController`

访问链接：`http://localhost:8010/webtool/env/author`

### 15. ehcache增删改查

`cn.afuo.webtool.ehcache.EhCacheController`

