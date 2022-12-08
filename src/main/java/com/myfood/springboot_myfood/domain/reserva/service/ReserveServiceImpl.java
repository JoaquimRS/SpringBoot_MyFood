package com.myfood.springboot_myfood.domain.reserva.service;

import com.myfood.springboot_myfood.domain.reserva.dto.ReserveDto;
import com.myfood.springboot_myfood.domain.reserva.entity.ReserveEntity;
import com.myfood.springboot_myfood.domain.reserva.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final ReserveRepository reserveRepository;

    private ReserveDto convertEntityToDto(ReserveEntity entity) {
        return ReserveDto
                .builder()
                .id_reserva(entity.getId_reserva())
                .id_cliente(entity.getId_cliente())
                .fecha(entity.getFecha())
                .n_comensales(entity.getN_comensales())
                .estado(entity.getEstado())
                .tipo(entity.getTipo())
                .build();
    }


    @Override
    public List<ReserveDto> getReserves() {
        return this.reserveRepository
                .findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public List<Object> getHolidays(Integer comensales, String servicio) {
        List<Object> finalList = new ArrayList<>();
        finalList.addAll(this.reserveRepository.getHolidays());
        finalList.addAll(this.reserveRepository.getBannedDays(comensales, servicio));

        return finalList;
    }

    @Override
    public ReserveDto getReserve(String id_reserva) {
        return convertEntityToDto(this.reserveRepository.findById(id_reserva).get());
    }
    
    @Override
    public ReserveDto saveReserve(ReserveEntity entity) {
        ReserveEntity saved = this.reserveRepository.saveReserve(entity);

        if (saved != null) {
            return convertEntityToDto(saved);
        }

        return null;
    }
}
