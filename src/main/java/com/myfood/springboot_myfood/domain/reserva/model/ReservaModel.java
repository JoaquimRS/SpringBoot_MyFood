package com.myfood.springboot_myfood.domain.reserva.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_reserva;

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
}
