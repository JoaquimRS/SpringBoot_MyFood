package com.myfood.springboot_myfood.domain.products.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryDto {
    @NotNull
    private String id_categoria;

    private String slug;

    @NotNull
    private String nombre;

    @NotNull
    private String icono;

    private List<String> productos;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SingleCategory<T> {
        private T category;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MultipleCategories {
        private List<CategoryDto> categories;
    }

}
