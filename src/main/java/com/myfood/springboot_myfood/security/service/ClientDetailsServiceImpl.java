package com.myfood.springboot_myfood.security.service;

import com.myfood.springboot_myfood.domain.clients.repository.ClientRepository;
import com.myfood.springboot_myfood.security.AuthClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findById(email)
                .map(clientEntity ->
                     AuthClientDetails.builder()
                            .id_cliente(clientEntity.getId_cliente())
                            .email(clientEntity.getEmail())
                            .build())
                .orElse(null);
    }
}
