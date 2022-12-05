package com.myfood.springboot_myfood.domain.pedidos.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    private String id_pedido;

    @Column(name = "id_cliente")
    private String id_cliente;
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "productos_pedidos",
            joinColumns = { @JoinColumn(name = "id_pedido") }
    )
    private Set<ProductEntity> pedidos = new HashSet<>();

    @Builder
    public PedidoEntity(String id_pedido, String id_cliente, Date fecha, String estado) {
        this.id_cliente = id_cliente;
        this.id_pedido = id_pedido;
        this.fecha = fecha;
        this.estado = estado;
    }
}
