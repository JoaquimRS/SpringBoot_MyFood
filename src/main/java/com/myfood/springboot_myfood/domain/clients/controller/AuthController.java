package com.myfood.springboot_myfood.domain.clients.controller;

import com.myfood.springboot_myfood.domain.clients.dto.ClientDto;
import com.myfood.springboot_myfood.domain.clients.service.ClientService;
import com.myfood.springboot_myfood.domain.payload.request.LoginRequest;
import com.myfood.springboot_myfood.domain.payload.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService cService;

    @PostMapping(name = "/register")
    public ClientDto register(@RequestBody @Valid SignUpRequest data) {
        return cService.registration(data);
    }

    @PostMapping(name = "/login")
    public ClientDto login(@RequestBody @Valid LoginRequest credentials) {
        return cService.login(credentials);
    }
}
