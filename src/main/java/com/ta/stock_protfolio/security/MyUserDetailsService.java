package com.ta.stock_protfolio.security;

import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Value("${ADMIN_EMAIL}")
    private String ADMIN_EMAIL;
    @Value("${ADMIN_PASSWORD}")
    private String ADMIN_PASSWORD;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        if (user.getEmail().equals(ADMIN_EMAIL) && user.getPassword().equals(ADMIN_PASSWORD) ){
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
