package com.myfood.springboot_myfood.domain.reserva.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.myfood.springboot_myfood.domain.reserva.service.ReserveService;
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
import com.myfood.springboot_myfood.domain.reserva.dto.ReserveDto;
import com.myfood.springboot_myfood.domain.reserva.entity.ReserveEntity;
import com.myfood.springboot_myfood.errors.Error;
import com.myfood.springboot_myfood.plugins.IdGenerator;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservesController {
    @Autowired
    private ReserveService reserveService;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @GetMapping("/getBannedDays")
    List<Object> getHolidays(@RequestParam Integer comensales, @RequestParam String servicio) {
        return this.reserveService.getHolidays(comensales, servicio);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("../../../assets/test.jpg");
        return IOUtils.toByteArray(in);
    }
    
    @GetMapping(value ="/getReserva/{id_reserva}")
    public ResponseEntity<?> getReserva(@PathVariable String id_reserva) {
        ReserveDto reserve = this.reserveService.getReserve(id_reserva);
        List<JsonObject> list = new ArrayList<>();

        JsonElement json = gson.fromJson(reserve.toString(), JsonElement.class);

        json.getAsJsonObject().entrySet().forEach(e -> {
            JsonObject jsonE = new JsonObject();

            if (e.getKey().equals("tipo") || e.getKey().equals("fecha") || e.getKey().equals("n_comensales")) {
                jsonE.addProperty(e.getKey(), e.getValue().getAsString());
                list.add(jsonE);
            }
        });

        return new ResponseEntity<>(list.toString(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> postReserve(@RequestBody ReserveDto model) {
//        model.setId_reserva(IdGenerator.generateWithLength(10));
//
//        if (this.repository.checkReserve(model.getId_cliente(), model.getTipo(), model.getFecha()).size() > 0) {
//            return new ResponseEntity<>(Error.CLIENT_RESERVE_SAME_SERVICE.getMessage(), Error.CLIENT_RESERVE_SAME_SERVICE.getStatus());
//        }
//
//        ReserveEntity reserva = new ReserveEntity(model);
//        return new ResponseEntity<>(this.repository.save(reserva), HttpStatus.OK);
//    }
}