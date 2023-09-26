package com.gym.gym.base.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanPrinter {

    @Autowired
    private ApplicationContext applicationContext;

    public void printAllBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("Bean Name: " + beanName);
            Object bean = applicationContext.getBean(beanName);
            System.out.println("Bean Type: " + bean.getClass().getName());
            System.out.println("-----------------------------");
        }
    }
}