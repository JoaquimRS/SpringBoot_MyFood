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
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myfood.springboot_myfood.domain.reserva.dto.ReservaDto;
import com.myfood.springboot_myfood.domain.reserva.model.ReservaModel;
import com.myfood.springboot_myfood.domain.reserva.repository.ReservaRepository;
import com.myfood.springboot_myfood.errors.Error;
import com.myfood.springboot_myfood.plugins.IdGenerator;

@RestController
@RequestMapping("/asdf")
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    ReservaRepository repository;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @GetMapping
    List<Object> getHolidays(@RequestParam Integer comensales, @RequestParam String servicio) {
        List<Object> finalList = new ArrayList<>();

        finalList.addAll(this.repository.getHolidays());
        finalList.addAll(this.repository.getBannedDays(comensales, servicio));

        return finalList;
    }

    @GetMapping(value = "/test", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        InputStream in = getClass().getResourceAsStream("../../../assets/test.jpg");

        assert in != null;
        return IOUtils.toByteArray(in);
    }
    
    @GetMapping(value ="/getReserva/{id_reserva}")
    public ResponseEntity<?> getReserva(@PathVariable String id_reserva) {
        List<JsonObject> list = new ArrayList<>();
        ReservaModel model = this.repository.getReserva(id_reserva);

        JsonElement json = gson.fromJson(model.toString(), JsonElement.class);

        json.getAsJsonObject().entrySet().forEach(e -> {
            JsonObject jsonE = new JsonObject();

            if (e.getKey().equals("tipo") || e.getKey().equals("fecha") || e.getKey().equals("n_comensales")) {
                jsonE.addProperty(e.getKey(), e.getValue().getAsString());
                list.add(jsonE);
            }
        });

        return new ResponseEntity<>(list.toString(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postReserve(@RequestBody ReservaDto model) {
        model.setId_reserva(IdGenerator.generateWithLength(10));

        if (this.repository.checkReserve(model.getId_cliente(), model.getTipo(), model.getFecha()).size() > 0) {
            return new ResponseEntity<>(Error.CLIENT_RESERVE_SAME_SERVICE.getMessage(), Error.CLIENT_RESERVE_SAME_SERVICE.getStatus());
        }

        ReservaModel reserva = new ReservaModel(model);
        return new ResponseEntity<>(this.repository.save(reserva), HttpStatus.OK);
    }
}