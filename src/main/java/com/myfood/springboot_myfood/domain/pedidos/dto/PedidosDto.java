package com.myfood.springboot_myfood.domain.pedidos.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PedidosDto {
    @NotNull
    private String id_pedido;

    @NotNull
    private String id_cliente;

    @NotNull
    private Date fecha;

    @NotNull
    private String estado;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SinglePedido<T> {
        private T pedido;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiplePedidos {
        private List<PedidosDto> pedidos;
    }
}
