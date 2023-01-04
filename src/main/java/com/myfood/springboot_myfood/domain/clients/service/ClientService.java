package com.myfood.springboot_myfood.domain.clients.service;

import com.myfood.springboot_myfood.domain.clients.dto.ClientDto;
import com.myfood.springboot_myfood.domain.payload.request.LoginRequest;
import com.myfood.springboot_myfood.domain.payload.request.SignUpRequest;
import com.myfood.springboot_myfood.domain.payload.request.UpdateRequest;
import com.myfood.springboot_myfood.domain.payload.response.JWTResponse;
import com.myfood.springboot_myfood.security.AuthClientDetails;

import java.io.IOException;

public interface ClientService {
    JWTResponse registration(final SignUpRequest data);
    JWTResponse login(final LoginRequest credentials);
    ClientDto currentUser(final AuthClientDetails client);

    ClientDto update(UpdateRequest newData, final AuthClientDetails clientDetails);
}
