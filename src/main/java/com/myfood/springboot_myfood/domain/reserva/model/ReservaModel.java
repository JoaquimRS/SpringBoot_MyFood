package com.myfood.springboot_myfood.domain.reserva.model;

import java.sql.Date;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myfood.springboot_myfood.domain.reserva.dto.ReservaDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservas")
public class ReservaModel {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_reserva = "ID_RESERVA";

    private String id_cliente;
    private Date fecha;
    private String tipo;
    private Integer n_comensales;
    private String estado;

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(this);
    }

    public ReservaModel(ReservaDto model) {
        this.estado = model.getEstado();
        this.fecha = model.getFecha();
        this.id_cliente = model.getId_cliente();
        this.id_reserva = model.getId_reserva();
        this.n_comensales = model.getN_comensales();
        this.tipo = model.getTipo();
    }
}
