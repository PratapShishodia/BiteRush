package com.biterush.cart_service.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("within(com.biterush.cart_service.controller..*) || within(com.biterush.cart_service.service..*)")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        try {
            Object result = joinPoint.proceed();
            logger.atInfo()
                    .addKeyValue("event", "method_execution")
                    .addKeyValue("class", className)
                    .addKeyValue("method", methodName)
                    .addKeyValue("duration_ms", (System.nanoTime() - startTime) / 1_000_000)
                    .addKeyValue("status", "success")
                    .log("Method executed");
            return result;
        } catch (Throwable exception) {
            logger.atError()
                    .addKeyValue("event", "method_execution")
                    .addKeyValue("class", className)
                    .addKeyValue("method", methodName)
                    .addKeyValue("duration_ms", (System.nanoTime() - startTime) / 1_000_000)
                    .addKeyValue("status", "failure")
                    .addKeyValue("exception", exception.getClass().getSimpleName())
                    .log("Method execution failed", exception);
            throw exception;
        }
    }
}