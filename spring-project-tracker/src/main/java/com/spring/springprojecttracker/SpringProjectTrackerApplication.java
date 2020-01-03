package com.spring.springprojecttracker;

import com.spring.springprojecttracker.service.block.BlockService;
import com.spring.springprojecttracker.tracker.DaemonListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class SpringProjectTrackerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringProjectTrackerApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }
//    @Bean
//    public DaemonListener schedulerRunner() {
//        return new DaemonListener();
//    }
}
