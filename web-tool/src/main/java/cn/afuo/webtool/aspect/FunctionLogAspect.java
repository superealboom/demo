package cn.afuo.webtool.aspect;


import cn.afuo.webtool.annotation.FunctionLog;
import cn.afuo.webtool.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 打印方法的入参和出参
 */
@Aspect
@Component
@Slf4j
public class FunctionLogAspect {

    @Around("@annotation(functionLog)")
    public Object functionLogAop(ProceedingJoinPoint joinPoint, FunctionLog functionLog) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        // Method method = methodSignature.getMethod();
        log.info("方法路径:{},描述:「{}」", methodSignature.getDeclaringTypeName() + "." + methodSignature.getName(), functionLog.desc());
        Map<String, Object> inputMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            inputMap.put(parameterNames[i], args[i]);
        }
        log.info("入参:{}", JsonUtil.toJson(inputMap));
        Object proceed = joinPoint.proceed();
        log.info("出参:{}", JsonUtil.toJson(proceed));
        return proceed;
    }
}
