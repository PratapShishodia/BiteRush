package com.biterush.auth_serivce.utils;

import com.biterush.auth_serivce.model.entity.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UserCredentials userCredentials;

    public CustomUserDetails(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userCredentials.getPasswordHashed();
    }

    @Override
    public String getUsername() {
        return userCredentials.getEmail();
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

}
