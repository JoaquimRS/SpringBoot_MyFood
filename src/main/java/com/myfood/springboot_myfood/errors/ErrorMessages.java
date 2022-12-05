package com.myfood.springboot_myfood.errors;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;

@Getter
@JsonTypeName("errors")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class ErrorMessages {
    private final List<String> body;

    public ErrorMessages() {
        body = new ArrayList<String>();
    }

    public void append(String message) {
        body.add(message);
    }

}