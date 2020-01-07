package com.spring.springprojecttracker.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springprojecttracker.security.auth.BaseSecurityHandler;
import com.spring.springprojecttracker.security.auth.ajax.AjaxAuthenticationProvider;
import com.spring.springprojecttracker.security.auth.ajax.filter.AjaxAuthenticationFilter;
import com.spring.springprojecttracker.security.auth.jwt.JwtAuthenticationProvider;
import com.spring.springprojecttracker.security.auth.jwt.filter.JwtAuthenticationFilter;
import com.spring.springprojecttracker.security.auth.jwt.matcher.SkipPathRequestMatcher;
import com.spring.springprojecttracker.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Arrays;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    private final CommonConfig commonConfig;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/block/**");
        web.ignoring().antMatchers("/token/**");
        web.ignoring().antMatchers("/error/**");
        web.ignoring().antMatchers("/api/**");
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/user/myinfo/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll();

//        http.csrf().disable();
        http.formLogin()
                .loginPage("/user/login")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/user/login/result", true)
                .usernameParameter("username")
                .passwordParameter("password");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")
                .invalidateHttpSession(true);

        http.exceptionHandling().accessDeniedPage("/user/denied");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(commonConfig.passwordEncoder());

//        auth.authenticationProvider(ajaxProvider)
//                .authenticationProvider(jwtProvider);
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER");
//    }



}
