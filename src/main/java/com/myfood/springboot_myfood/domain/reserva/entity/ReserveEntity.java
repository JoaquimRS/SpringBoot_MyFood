package com.myfood.springboot_myfood.domain.reserva.entity;

import com.myfood.springboot_myfood.domain.common.utils.BaseUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "reservas")
public class ReserveEntity extends BaseUtils {
    @Id
    private String id_reserva;

    @Column(name = "id_cliente")
    private String id_cliente;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "n_comensales")
    private Integer n_comensales;
    @Column(name = "estado")
    private String estado;

//    @Builder
//    public ReserveEntity(ReserveDto model) {
//        this.estado = model.getEstado();
//        this.fecha = model.getFecha();
//        this.id_cliente = model.getId_cliente();
//        this.id_reserva = model.getId_reserva();
//        this.n_comensales = model.getN_comensales();
//        this.tipo = model.getTipo();
//    }
}
