package com.myfood.springboot_myfood.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {
    DUPLICATED_USER("There is duplicated user information", HttpStatus.UNPROCESSABLE_ENTITY),
    LOGIN_INFO_INVALID("Login information is invalid", HttpStatus.UNPROCESSABLE_ENTITY),
    USER_NOT_FOUND("User not found", HttpStatus.UNPROCESSABLE_ENTITY),
    ;

    private final String message;
    private final HttpStatus status;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
