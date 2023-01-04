package com.myfood.springboot_myfood.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String id) {
        return Optional.ofNullable(id)
                .map(userDetailsService::loadUserByUsername)
                .map(clientDetails ->
                        new UsernamePasswordAuthenticationToken(
                                clientDetails,
                                clientDetails.getPassword(),
                                clientDetails.getAuthorities()))
                .orElse(null);
    }
}
