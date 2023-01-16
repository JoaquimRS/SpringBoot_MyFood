package com.myfood.springboot_myfood.domain.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateRequest {
    @Size(min = 3, max = 30)
    @Getter
    @Setter
    private String nombre;

    @Size(min = 8, max = 40)
    @Getter
    @Setter
    private String contraseña;

    @Size(min = 9, max = 9)
    @Getter
    @Setter
    private String telefono;
}
