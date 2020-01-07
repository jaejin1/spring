package com.spring.springprojecttracker.service.user;

import com.spring.springprojecttracker.domain.user.Role;
import com.spring.springprojecttracker.domain.user.UserEntity;
import com.spring.springprojecttracker.domain.user.UserRepository;
import com.spring.springprojecttracker.dto.user.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private static final String ADMIN_EMAIL = "admin@example.com";

    @Transactional
    public UserEntity joinUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (CheckIsAdminEmail(userDto.getEmail())) {
            userDto.setRole(Role.ADMIN.getValue());
        } else {
            userDto.setRole(Role.USER.getValue());
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (CheckIsAdminEmail(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    private boolean CheckIsAdminEmail(String userEmail) {
        return (ADMIN_EMAIL).equals(userEmail);
    }
}
