package com.myfood.springboot_myfood.domain.pedidos.dto;

import java.util.Currency;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductoDto {
    @NotNull
    private String id_producto;
    
    private String slug;

    @NotNull
    private String nombre;
    @NotNull
    private Currency precio;
    @NotNull
    private String imagen;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SingleProducto<T> {
        private T producto;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class MultipleProductos {
        private List<ProductoDto> productos;
    }

}
