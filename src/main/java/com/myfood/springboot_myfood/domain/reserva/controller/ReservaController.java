package com.myfood.springboot_myfood.domain.reserva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfood.springboot_myfood.domain.reserva.repository.ReservaRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    ReservaRepository repository;

    @GetMapping
    List<?> getHolidays() {
        return this.repository.getHolidays();
    }
}