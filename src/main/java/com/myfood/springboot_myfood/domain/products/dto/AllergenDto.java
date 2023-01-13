package com.myfood.springboot_myfood.domain.products.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AllergenDto {
    @NotNull
    private String id_alergeno;

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
    public static class SingleAllergen<T> {
        private T allergen;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MultipleAllergens {
        private List<AllergenDto> allergens;
    }
}
