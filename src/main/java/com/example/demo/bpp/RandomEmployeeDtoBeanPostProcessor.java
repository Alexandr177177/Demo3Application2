package com.example.demo.bpp;

import com.example.demo.annotations.RandomEmployeeDto;
import com.example.demo.annotations.SimpleLog;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.util.Random;
import org.springframework.beans.BeansException;

import java.lang.reflect.Field;

public class RandomEmployeeDtoBeanPostProcessor {


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();

        // Проходим по всем полям класса
        for (Field field : clazz.getDeclaredFields()) {
            // Проверяем, есть ли аннотация @RandomEmployeeDto
            if (field.isAnnotationPresent(RandomEmployeeDto.class)) {
                field.setAccessible(true); // Даем доступ к приватному полю

                try {
                    // Проверяем, что поле имеет тип EmployeeDto
                    if (field.getType().equals(EmployeeDto.class)) {
                        // Генерируем случайный объект EmployeeDto
                        EmployeeDto randomEmployee = Random.getRandomEmployeeDto();
                        // Устанавливаем значение поля
                        field.set(bean, randomEmployee);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Не удалось установить значение для поля: " + field.getName(), e);
                }
            }
        }

        return bean;


    }
}
