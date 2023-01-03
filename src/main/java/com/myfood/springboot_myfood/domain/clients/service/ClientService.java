package com.myfood.springboot_myfood.domain.clients.service;

import com.myfood.springboot_myfood.domain.clients.dto.ClientDto;
import com.myfood.springboot_myfood.domain.payload.request.LoginRequest;
import com.myfood.springboot_myfood.domain.payload.request.SignUpRequest;
import com.myfood.springboot_myfood.security.AuthClientDetails;

public interface ClientService {
    ClientDto registration(final SignUpRequest data);
    ClientDto login(final LoginRequest credentials);
    ClientDto currentUser(final AuthClientDetails authClientDetails);
}
