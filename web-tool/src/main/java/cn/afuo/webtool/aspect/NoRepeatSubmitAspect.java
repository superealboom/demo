package cn.afuo.webtool.aspect;


import cn.afuo.webtool.annotation.NoRepeatSubmit;
import cn.afuo.webtool.constant.RedisConstants;
import cn.afuo.webtool.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


/**
 * 防重提交AOP
 */
@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAspect {

    private final RedissonClient redissonClient;

    public NoRepeatSubmitAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(noRepeatSubmit)")
    public Object noRepeatSubmitAop(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        // 入参
        StringBuilder inputDatas = new StringBuilder("#");
        String inputKeys = noRepeatSubmit.appointParamName();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (Arrays.asList(inputKeys.split(",")).contains(parameterNames[i])) {
                inputDatas.append(args[i]);
            }
        }

        String lockName = RedisConstants.COMMON_LOCK_PREFIX + joinPoint.getTarget().getClass().getName() + "#" + targetMethod.getName() + inputDatas;
        log.info("lockName:{}", lockName);

        RLock lock = redissonClient.getLock(lockName);
        // 参数：尝试获取锁的最大等待时间、锁持有的超时时间、两个时间参数的单位
        boolean flag = lock.tryLock(0, noRepeatSubmit.timeout(), TimeUnit.MILLISECONDS);
        if (!flag) {
            return Result.fail().message("获取锁失败:" + lockName);
        }
        return joinPoint.proceed();
    }

}
