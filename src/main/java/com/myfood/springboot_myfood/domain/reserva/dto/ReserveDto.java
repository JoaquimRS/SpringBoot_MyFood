package com.myfood.springboot_myfood.domain.reserva.dto;

import java.util.List;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReserveDto {
    private String id_reserva;

    @NotBlank
    private String id_cliente;

    @NotBlank
    private LocalDate fecha;

    @NotBlank
    private String tipo;

    @NotNull
    private Integer n_comensales;

    private String estado;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleReserva<T> {
        private T reserva;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleReserva {
        private List<ReserveDto> reservas;
    }

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(this);
    }
}