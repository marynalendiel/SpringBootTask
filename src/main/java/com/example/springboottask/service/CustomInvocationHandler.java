package com.example.springboottask.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LogManager.getLogger(CustomInvocationHandler.class);

    private final UserService userService;

    public CustomInvocationHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (AnnotationUtils.findAnnotation(method, Snapshot.class) != null) {
            LOGGER.info("(@ProxyBefore) Executing method: {} with input param: {}", method.getName(), args);
        }

        return method.invoke(userService, args);
    }
}


//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//
//        Method[] methods = bean.getClass().getMethods();
//        for (Method method : methods) {
//            Snapshot annotation = method.getAnnotation(Snapshot.class);
//            Object returnObj = null;
//            if (annotation != null) {
//                System.out.println("(@Before) Executing method: with input param: " + method.getName());
//                logger.info("(@Before) Executing method: {} with input param", method.getName());
//                try {
//                    returnObj = Proxy.getInvocationHandler(bean).invoke(bean, method, method.getParameters());
//                } catch (Throwable e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//    }
