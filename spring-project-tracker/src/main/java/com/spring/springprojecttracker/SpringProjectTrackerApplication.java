package com.spring.springprojecttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringProjectTrackerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringProjectTrackerApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

}
