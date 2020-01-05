package com.spring.springprojecttracker.security.auth;

import com.spring.springprojecttracker.domain.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserDetailsImpl extends User {

    public UserDetailsImpl(String id, List<GrantedAuthority> authorities) {
        super(id, "", authorities);
    }

    public UserDetailsImpl(UserEntity user, List<GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
    }
}
