package com.bitberush.order_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Around("execution(* com.bitberush.order_service.service..*(..))")
    public Object logServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("Service method started - class: {}, method: {}, args: {}",
                className, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("Service method completed - class: {}, method: {}, executionTimeMs: {}, result: {}",
                    className, methodName, elapsedTime, result);
            return result;
        } catch (Exception ex) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.error("Service method failed - class: {}, method: {}, executionTimeMs: {}, error: {}",
                    className, methodName, elapsedTime, ex.getMessage(), ex);
            throw ex;
        }
    }
}
