package com.myfood.springboot_myfood.domain.clients.dto;

import com.myfood.springboot_myfood.domain.common.utils.BaseUtils;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto extends BaseUtils {
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
//    @JsonIgnore
    @Size(min = 8, max = 40)
    private String contraseña;

    @NotNull
    @NotBlank
    private String avatar;

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
