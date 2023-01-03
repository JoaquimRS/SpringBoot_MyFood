package com.myfood.springboot_myfood.domain.clients.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Builder
public class ClientDto {
    @NotNull
    @NotBlank
    private String id_cliente;

    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String telefono;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 40)
    private String contraseña;

    private String token;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SingleClient<T> {
        private T client;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MultipleClients {
        private List<ClientDto> clients;
    }
}
