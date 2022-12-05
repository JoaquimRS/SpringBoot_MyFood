package com.myfood.springboot_myfood.domain.reserva.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReservaDto {
    private String id_reserva;

    @NotBlank
    private String id_cliente;

    @NotBlank
    private Date fecha;

    @NotBlank
    private String tipo;

    @Min(2)
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
        private List<ReservaDto> reservas;
    }
}