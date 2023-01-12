package com.myfood.springboot_myfood.domain.orders.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    @NotNull
    private String id_pedido;

    @NotNull
    private String id_cliente; 

    @NotNull
    private LocalDate fecha;

    @NotNull
    private String estado;

    private List<?> productos_pedidos;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SingleOrder<T> {
        private T order;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class MultipleOrders {
        private List<OrderDto> orders;
    }
}
