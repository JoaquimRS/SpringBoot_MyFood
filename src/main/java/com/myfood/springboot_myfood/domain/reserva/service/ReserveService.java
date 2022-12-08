package com.myfood.springboot_myfood.domain.reserva.service;

import com.myfood.springboot_myfood.domain.reserva.dto.ReserveDto;
import com.myfood.springboot_myfood.domain.reserva.entity.ReserveEntity;

import java.util.List;

public interface ReserveService {
    List<ReserveDto> getReserves();
    List<Object> getHolidays(Integer comensales, String servicio);

    ReserveDto getReserve(String id_reserva);

    ReserveDto saveReserve(ReserveEntity entity);
}
