package com.myfood.springboot_myfood.domain.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JWTResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String nombre;
    private String email;
}
