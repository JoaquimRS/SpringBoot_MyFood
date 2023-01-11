package com.myfood.springboot_myfood.domain.clients.controller;

import ch.qos.logback.core.net.server.Client;
import com.myfood.springboot_myfood.domain.clients.dto.ClientDto;
import com.myfood.springboot_myfood.domain.clients.service.ClientService;
import com.myfood.springboot_myfood.domain.payload.request.UpdateRequest;
import com.myfood.springboot_myfood.security.AuthClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService cService;

    @GetMapping(path = "/profile")
    public ClientDto profile(@AuthenticationPrincipal AuthClientDetails authClientDetails) {
        return cService.currentUser(authClientDetails);
    }

    @PutMapping(path = "/updateProfile")
    public ClientDto update(@RequestBody @Valid UpdateRequest uRequest, @AuthenticationPrincipal AuthClientDetails authClientDetails) {
        return cService.update(uRequest, authClientDetails);
    }
}
