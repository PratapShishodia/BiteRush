package com.biterush.payment_service.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.biterush.payment_service.service..*(..)) || execution(* com.biterush.payment_service.controller..*(..))")
    public void applicationLayer() {
    }

    @Around("applicationLayer()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Entering {}.{} with arguments: {}", className, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("Exiting {}.{} with result: {} in {} ms", className, methodName, result, elapsedTime);
            return result;
        } catch (Exception ex) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.error("Exception in {}.{} after {} ms with message: {}", className, methodName, elapsedTime, ex.getMessage(), ex);
            throw ex;
        }
    }

    @AfterThrowing(pointcut = "applicationLayer()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception thrown in {}.{}: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                exception.getMessage(),
                exception);
    }
}
