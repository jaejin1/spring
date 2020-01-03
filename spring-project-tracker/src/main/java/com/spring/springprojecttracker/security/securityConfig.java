package com.spring.springprojecttracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/block/**");
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/myinfo/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll();
//                .antMatchers("/", "/login", "/login-error").permitAll()
//                .antMatchers("/**").authenticated();

//        http.csrf().disable();
//
        http.formLogin()
                .loginPage("/user/login")
//                .loginProcessingUrl("/user/login")
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
