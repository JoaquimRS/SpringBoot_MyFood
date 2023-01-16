package com.myfood.springboot_myfood.domain.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderProductDto {
    private String id_pedido;
    private String id_producto;
    private Integer cantidad;
}
