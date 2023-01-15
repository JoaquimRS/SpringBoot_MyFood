package com.myfood.springboot_myfood.domain.clients.controller;

import com.myfood.springboot_myfood.domain.clients.service.ClientService;
import com.myfood.springboot_myfood.domain.payload.request.LoginRequest;
import com.myfood.springboot_myfood.domain.payload.request.SignUpRequest;
import com.myfood.springboot_myfood.domain.payload.response.JWTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService cService;

    @PostMapping(path = "/register")
    public JWTResponse register(@RequestBody @Valid SignUpRequest data) {
        return cService.registration(data);
    }

    @PostMapping(path = "/login")
    public JWTResponse login(@RequestBody @Valid LoginRequest credentials) {
        return cService.login(credentials);
    }
}
