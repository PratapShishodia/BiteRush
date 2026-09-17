package com.biterush.menu_service.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.biterush.menu_service.service..*(..)) || execution(* com.biterush.menu_service.controller..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("event=method_start class={} method={} arguments={}", className, methodName, args);

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long durationMs = System.currentTimeMillis() - startTime;
            log.info("event=method_end class={} method={} duration_ms={} result={}", className, methodName, durationMs, result);
            return result;
        } catch (Throwable ex) {
            long durationMs = System.currentTimeMillis() - startTime;
            log.error("event=method_exception class={} method={} duration_ms={} exception={} message={}",
                    className,
                    methodName,
                    durationMs,
                    ex.getClass().getName(),
                    ex.getMessage(),
                    ex);
            throw ex;
        }
    }
}
