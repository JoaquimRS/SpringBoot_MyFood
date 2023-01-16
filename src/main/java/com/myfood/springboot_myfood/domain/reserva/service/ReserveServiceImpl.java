package com.myfood.springboot_myfood.domain.reserva.service;

import com.myfood.springboot_myfood.domain.reserva.dto.ReserveDto;
import com.myfood.springboot_myfood.domain.reserva.entity.ReserveEntity;
import com.myfood.springboot_myfood.domain.reserva.repository.ReserveRepository;
import com.myfood.springboot_myfood.plugins.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    private ReserveEntity convertDtoToEntity(ReserveDto dto) {
        return ReserveEntity.builder()
                .tipo(dto.getTipo())
                .id_reserva(dto.getId_reserva())
                .estado(dto.getEstado())
                .fecha(dto.getFecha())
                .id_cliente(dto.getId_cliente())
                .n_comensales(dto.getN_comensales())
                .build();
    }

    @Override
    public List<ReserveDto> getReserves(String id_client) {
        return this.reserveRepository
                .findById_cliente(id_client)
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
    // TODO: Checkear que la reserva que se quiere crear no exista
    public ResponseEntity saveReserve(ReserveDto dto) {
        if (this.reserveRepository.save(convertDtoToEntity(dto)) != null) {
            return new ResponseEntity<>(dto.getId_reserva(), HttpStatus.OK);
        }

        return new ResponseEntity<>("{'msg': 'Hubo un error al crear la reserva'}", HttpStatus.BAD_REQUEST);
    }
}
