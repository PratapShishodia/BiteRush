package com.biterush.auth_serivce.utils;

import com.biterush.auth_serivce.model.entity.UserCredentials;
import com.biterush.auth_serivce.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials userCredentials = userCredentialsRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found"));
        return new CustomUserDetails(userCredentials);
    }
}
