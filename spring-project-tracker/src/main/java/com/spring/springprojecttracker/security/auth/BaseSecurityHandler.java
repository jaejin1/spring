package com.spring.springprojecttracker.security.auth;

import com.spring.springprojecttracker.security.auth.jwt.JwtInfo;
import com.spring.springprojecttracker.security.auth.jwt.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Component
public class BaseSecurityHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {
        UserDetails userDetails = new UserDetailsImpl(authentication.getPrincipal().toString(), new ArrayList<>(authentication.getAuthorities()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String token = JwtUtil.createToken(userDetails);
        response.setHeader(JwtInfo.HEADER_NAME, token);

        System.out.println(request.getSession().getId());
        System.out.println(token);
        Cookie sessionCookie = new Cookie(request.getSession().getId(), token);
        response.addCookie(sessionCookie);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
    }
}
