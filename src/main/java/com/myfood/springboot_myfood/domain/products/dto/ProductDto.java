package com.myfood.springboot_myfood.domain.products.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.myfood.springboot_myfood.domain.products.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDto {
    @NotNull
    private String id_producto;

    @NotNull
    private String imagen;

    @NotNull
    private String nombre;

    @NotNull
    private BigDecimal precio;

    private String slug;

    private List<String> categorias;

    private List<String> alergenos;

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
        private List<ProductDto> productos;
    }

}
