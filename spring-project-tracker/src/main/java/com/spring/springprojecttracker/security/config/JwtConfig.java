package com.spring.springprojecttracker.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springprojecttracker.security.auth.BaseSecurityHandler;
import com.spring.springprojecttracker.security.auth.ajax.AjaxAuthenticationProvider;
import com.spring.springprojecttracker.security.auth.ajax.filter.AjaxAuthenticationFilter;
import com.spring.springprojecttracker.security.auth.jwt.JwtAuthenticationProvider;
import com.spring.springprojecttracker.security.auth.jwt.filter.JwtAuthenticationFilter;
import com.spring.springprojecttracker.security.auth.jwt.matcher.SkipPathRequestMatcher;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class JwtConfig {

//    private final JwtAuthenticationProvider jwtProvider;
//
//    private final AjaxAuthenticationProvider ajaxProvider;
//
//    private final BaseSecurityHandler securityHandler;
//
//    private final ObjectMapper objectMapper;
//
//    private static final String LOGIN_ENTRY_POINT = "/user/login";
//    private static final String TOKEN_ENTRY_POINT = "/token";
//    private static final String ERROR_ENTRY_POINT = "/error";
//
//    @Bean
//    public AntPathRequestMatcher antPathRequestMatcher(){
//        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
//    }
//
//    @Bean
//    public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
//        AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setAuthenticationSuccessHandler(securityHandler);
//        filter.setAuthenticationFailureHandler(securityHandler);
//        return filter;
//    }
//
//    @Bean
//    public SkipPathRequestMatcher skipPathRequestMatcher() {
//        return new SkipPathRequestMatcher(Arrays.asList("/hello" ,"/hi", "/user/signup", "/index", LOGIN_ENTRY_POINT, TOKEN_ENTRY_POINT, ERROR_ENTRY_POINT));
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setAuthenticationFailureHandler(securityHandler);
//        return filter;
//    }
}
