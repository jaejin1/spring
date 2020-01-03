//package com.spring.springprojecttracker.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthProvider implements AuthenticationProvider {
//
//    @Autowired
//    AuthorizationService authorizationService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String id = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        return authenticate(id, password);
//    }
//
//    public Authentication authenticate(String id, String password) throws AuthenticationException {
//        User user = authorizationService.login(id, password);
//    }
//
//
//}
