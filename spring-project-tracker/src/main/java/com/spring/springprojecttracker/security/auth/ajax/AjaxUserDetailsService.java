package com.spring.springprojecttracker.security.auth.ajax;

import com.spring.springprojecttracker.domain.user.UserEntity;
import com.spring.springprojecttracker.domain.user.UserRepository;
import com.spring.springprojecttracker.security.auth.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AjaxUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String useremail) {
        UserEntity user = userRepository.findByEmail(useremail).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(useremail + "라는 사용자가 없습니다.");
        }

        return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(user.getRole()));
    }
}
