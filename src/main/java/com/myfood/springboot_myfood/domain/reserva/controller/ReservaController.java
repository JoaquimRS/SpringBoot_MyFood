package com.myfood.springboot_myfood.domain.reserva.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfood.springboot_myfood.domain.reserva.repository.ReservaRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    ReservaRepository repository;

    @GetMapping
    List<Object> getHolidays(@RequestParam Integer comensales) {
        List<Object> finalList = new ArrayList<Object>();

        for(Object holiday : this.repository.getHolidays()) {
            finalList.add(holiday);
        }

        for(Object bannedDay : this.repository.getBannedDays(comensales)) {
            finalList.add(bannedDay);
        }

        return finalList;
    }
}