package com.myfood.springboot_myfood.domain.reserva.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myfood.springboot_myfood.domain.reserva.model.ReservaModel;
import com.myfood.springboot_myfood.domain.reserva.payload.ReservaPDFResponse;
import com.myfood.springboot_myfood.domain.reserva.repository.ReservaRepository;
import com.myfood.springboot_myfood.plugins.IdGenerator;

@RestController
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    ReservaRepository repository;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

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

    @GetMapping("/reservas/{id_reserva}")
    public ResponseEntity<?> getReserva(@PathVariable String id_reserva) {
        List<JsonObject> list = new ArrayList<>();
        ReservaModel model = this.repository.getReserva(id_reserva);

        JsonElement json = gson.fromJson(model.toString(), JsonElement.class);
        
        json.getAsJsonObject().entrySet().forEach(e -> {
            JsonObject jsonE = new JsonObject();
            
            if (e.getKey().equals("tipo")|| e.getKey().equals("fecha") || e.getKey().equals("n_comensales")) {
                jsonE.addProperty(e.getKey(), e.getValue().getAsString());
                list.add(jsonE);
            }
        });


        return new ResponseEntity<>(list.toString(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postReserve(@RequestBody ReservaModel model) {
        model.setId_reserva(IdGenerator.generateWithLength(10));

        // this.repository.save(model);
        return new ResponseEntity<>(model, HttpStatus.OK);

        // this.repository.save(model);

        // ReservaModel reserved = this.repository.getReserva(model.getId_reserva());


        // if (reserved != null) {
        //     JsonObject json = new JsonObject();
        //     json.addProperty("msg", "Saved");

        //     return new ResponseEntity<>(json.getAsString(), HttpStatus.OK);
        // }

        // return new ResponseEntity<>(model, HttpStatus.OK);
    }
}