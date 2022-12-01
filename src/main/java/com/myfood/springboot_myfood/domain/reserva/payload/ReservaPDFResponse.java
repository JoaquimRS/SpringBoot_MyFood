package com.myfood.springboot_myfood.domain.reserva.payload;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservaPDFResponse {
    private Date fecha;
    private String tipo;
    private Integer n_comensales;
}
