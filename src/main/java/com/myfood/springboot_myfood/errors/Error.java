package com.myfood.springboot_myfood.errors;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
    CLIENT_RESERVE_SAME_SERVICE("Ya tiene una reserva para ese servicio, porfavor revise la información de reserva.", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String message;
    private final HttpStatus status;
}
