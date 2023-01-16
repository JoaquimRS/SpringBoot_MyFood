package com.myfood.springboot_myfood.domain.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "productos_pedidos")
@IdClass(OrderProductId.class)
public class OrderProductEntity {
    @Id
    @Column(name = "id_pedido")
    private String id_pedido;
    
    @Id
    @Column(name = "id_producto")
    private String id_producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private OrderEntity pedidos;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private ProductEntity productos;

    @Builder
    public OrderProductEntity(String id_pedido, String id_producto, Integer cantidad) {
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }


}
