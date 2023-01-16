package com.myfood.springboot_myfood.domain.orders.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductId implements Serializable{
    private String id_pedido;
    private String id_producto;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        OrderProductId that = (OrderProductId) o;
        return Objects.equals(getId_pedido(), that.getId_pedido()) &&
               Objects.equals(getId_producto(), that.getId_producto());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getId_pedido(), getId_producto());
    }
}   
