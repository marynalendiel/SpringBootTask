package com.example.springboottask.service;

import com.example.springboottask.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class UserAspect {
    private static final Logger LOGGER = LogManager.getLogger(UserAspect.class);
    @Pointcut("@annotation(com.example.springboottask.service.Snapshot)")
    public void snapshotMethods() {}

    @Before("snapshotMethods()")
    public void logMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> methodParam = Arrays.asList(joinPoint.getArgs());
        LOGGER.info("(@Before) Executing method: {} with input param: {}", methodName, methodParam);
    }

//    @AfterReturning(pointcut = "@annotation(com.example.springboottask.service.Snapshot)",
    @AfterReturning(pointcut = "execution(* com.example.springboottask.service.UserServiceImpl.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, List<User> result) {
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("(@AfterReturning) Executing method: {} with input param: {}", methodName, result);
    }

    @Around("execution(* com.example.springboottask.service.UserServiceImpl.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        LOGGER.info("(@Around) Executing method: {}", methodName);

        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        LOGGER.info("(@Around) Duration: {} seconds", duration / 1000.0);

        return result;
    }
}