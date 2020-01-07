package com.spring.springprojecttracker.tracker;

import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;

@UtilityClass
public class BeanUtils {

    public static Object getBean(String beanName) {

        ApplicationContext applicationContext = null;

        while (applicationContext == null) {
            try{
                Thread.sleep(100);
                applicationContext = ApplicationContextProvider.getApplicationContext();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return applicationContext.getBean(beanName);
    }
}
