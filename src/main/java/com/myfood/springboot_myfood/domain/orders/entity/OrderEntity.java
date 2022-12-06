package com.myfood.springboot_myfood.domain.orders.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
@NamedEntityGraph(name = "fetch-orders")
public class OrderEntity {
    @Id
    private String id_pedido;

    @Column(name = "id_cliente")
    private String id_cliente;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    @Builder
    public OrderEntity(String id_pedido, String id_cliente, Date fecha, String estado) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.estado = estado;
    }
}
