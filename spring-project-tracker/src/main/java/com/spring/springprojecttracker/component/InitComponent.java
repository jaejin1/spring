//package com.spring.springprojecttracker.component;
//
//import com.spring.springprojecttracker.domain.user.UserEntity;
//import com.spring.springprojecttracker.domain.user.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//
//@Component
//@AllArgsConstructor
//public class InitComponent implements ApplicationRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Transactional
//    @Override
//    public void run(ApplicationArguments args) {
//        UserEntity user = new UserEntity(0L, "test", passwordEncoder.encode("1234"),"USER");
//        userRepository.save(user);
//    }
//}
