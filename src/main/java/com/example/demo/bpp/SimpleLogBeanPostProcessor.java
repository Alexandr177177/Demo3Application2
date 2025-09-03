package com.example.demo.bpp;

import com.example.demo.annotations.SimpleLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
    public class SimpleLogBeanPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

            if (bean.getClass().isAnnotationPresent(SimpleLog.class)) {
                SimpleLog annotation = bean.getClass().getAnnotation(SimpleLog.class);
                String message = annotation.value();

                if (message.isEmpty()) {
                    System.out.println("🔹 Найден класс с @SimpleLog: " +
                            beanName + "." + bean.getClass().getName());
                } else {
                    System.out.println("🔹 " + message + ": " +
                            beanName + "." + bean.getClass().getName());
                }
            }

            return bean;
        }
    }
