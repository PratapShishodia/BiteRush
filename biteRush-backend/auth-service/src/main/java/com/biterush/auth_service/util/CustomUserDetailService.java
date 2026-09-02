package com.biterush.auth_service.util;

import com.biterush.auth_service.model.entity.UserCredentials;
import com.biterush.auth_service.repository.UserCredentialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserCredentialsRepo userCredentialsRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredentials userCredentials = userCredentialsRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found!"));
        return new CustomUserDetails(userCredentials);
    }
}
