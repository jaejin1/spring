package com.spring.springprojecttracker.tracker;

import com.spring.springprojecttracker.api.BlockController;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
