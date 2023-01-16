package com.myfood.springboot_myfood.domain.reserva.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.myfood.springboot_myfood.domain.common.utils.BaseUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveDto extends BaseUtils {
    private String id_reserva;

    @NotBlank
    private String id_cliente;

    @NotBlank
    private LocalDate fecha;

    @NotBlank
    private String tipo;

    @NotNull
    @Min(2)
    private Integer n_comensales;

    private String estado;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleReserva<T> {
        private T reserva;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleReserva {
        private List<ReserveDto> reservas;
    }
}