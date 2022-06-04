package me.hyeonkyo.todo.global.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("execution(* me.hyeonkyo.todo.domain.**.service.*.*(..))")
    public Object logMethodRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("REQUEST {}({}) -----> args : {} ", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());

        long startAt = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endAt = System.currentTimeMillis();

        log.info("RESPONSE {}({}) -----> return(size) : {}, ({}ms)", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result instanceof List ? ((List) result).size() : result,
                endAt - startAt);

        return result;
    }
}
