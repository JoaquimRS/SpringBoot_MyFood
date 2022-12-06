package com.myfood.springboot_myfood.domain.orders.dto;

import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDto {
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
