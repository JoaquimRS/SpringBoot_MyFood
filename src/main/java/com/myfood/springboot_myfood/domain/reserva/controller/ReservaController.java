package com.myfood.springboot_myfood.domain.reserva.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myfood.springboot_myfood.domain.reserva.repository.ReservaRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    ReservaRepository repository;

    @GetMapping
    List<Object> getHolidays(@RequestParam Integer comensales, @RequestParam String servicio) {
        List<Object> finalList = new ArrayList<Object>();

        for(Object holiday : this.repository.getHolidays()) {
            finalList.add(holiday);
        }

        for(Object bannedDay : this.repository.getBannedDays(comensales, servicio)) {
            finalList.add(bannedDay);
        }

        return finalList;
    }

    @GetMapping(value = "/test",   produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        InputStream in = getClass().getResourceAsStream("../../../assets/test.jpg");

        return IOUtils.toByteArray(in);
    }
}