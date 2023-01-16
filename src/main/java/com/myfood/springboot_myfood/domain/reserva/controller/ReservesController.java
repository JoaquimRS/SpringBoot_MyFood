package com.myfood.springboot_myfood.domain.reserva.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.myfood.springboot_myfood.domain.reserva.service.ReserveService;
import com.myfood.springboot_myfood.plugins.IdGenerator;
import com.myfood.springboot_myfood.security.AuthClientDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myfood.springboot_myfood.domain.clients.service.ClientService;
import com.myfood.springboot_myfood.domain.reserva.dto.ReserveDto;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservesController {
    @Autowired
    private ReserveService reserveService;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @Autowired
    private ClientService cService;

    @GetMapping
    ReserveDto.MultipleReserva getReserves(@AuthenticationPrincipal AuthClientDetails authClientDetails) {
        return ReserveDto.MultipleReserva
                .builder()
                .reservas(this.reserveService.getReserves(cService.currentUser(authClientDetails).getId_cliente()))
                .build();
    }

    @GetMapping("/getBannedDays")
    List<Object> getBannedDays(@RequestParam Integer comensales, @RequestParam String servicio) {
        return this.reserveService.getHolidays(comensales, servicio);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody void getImage(HttpServletResponse response) throws IOException {
        var image = new ClassPathResource("images/test.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(image.getInputStream(), response.getOutputStream());
    }

    @GetMapping(value = "/{id_reserva}")
    public ResponseEntity<?> getReserve(@PathVariable String id_reserva) {
        return new ResponseEntity<>(this.reserveService.getReserve(id_reserva), HttpStatus.OK);
    }

    @GetMapping(value ="/pdf/{id_reserva}")
    public ResponseEntity<?> getPDFReserve(@PathVariable String id_reserva) {
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

    @PostMapping
    public ResponseEntity createReserve(@AuthenticationPrincipal AuthClientDetails authClientDetails, @RequestBody Object body) {
        ReserveDto dto = new ReserveDto();
        JsonObject json = gson.fromJson(gson.toJson(body), JsonObject.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dt = LocalDate.parse(json.get("fecha").getAsString(), formatter);

        dto.setId_reserva(IdGenerator.generateWithLength(10));
        dto.setId_cliente(cService.currentUser(authClientDetails).getId_cliente());
        dto.setFecha(dt);
        dto.setTipo(json.get("servicio").getAsString());
        dto.setN_comensales(json.get("n_comensales").getAsInt());
        dto.setEstado("PENDIENTE");

        return this.reserveService.saveReserve(dto);
    }
}