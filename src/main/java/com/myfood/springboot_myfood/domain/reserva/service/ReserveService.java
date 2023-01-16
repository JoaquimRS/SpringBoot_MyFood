package com.myfood.springboot_myfood.domain.reserva.service;

import com.myfood.springboot_myfood.domain.reserva.dto.ReserveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReserveService {
    List<ReserveDto> getReserves(String id_client);
    List<Object> getHolidays(Integer comensales, String servicio);

    ReserveDto getReserve(String id_reserva);

    ResponseEntity saveReserve(ReserveDto entity);

}
