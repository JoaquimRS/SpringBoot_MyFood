package com.myfood.springboot_myfood.domain.products.service;

import com.myfood.springboot_myfood.domain.products.dto.AllergenDto;
import com.myfood.springboot_myfood.domain.products.entity.AllergenEntity;
import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;
import com.myfood.springboot_myfood.domain.products.repository.AllergenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergenServiceImpl implements AllergenService{
    private final AllergenRepository allergenRepository;

    private AllergenDto convertoToDto(AllergenEntity entity) {
        return AllergenDto.builder()
                .id_alergeno(entity.getId_alergeno())
                .nombre(entity.getNombre())
                .icono(entity.geticono())
                .productos(entity.getProductos()
                        .stream()
                        .map(ProductEntity::getId_producto)
                        .toList())
                .build();
    }

    @Transactional
    @Override
    public List<AllergenDto> getAllergens() {
        return this.allergenRepository
                .findAll()
                .stream()
                .map(this::convertoToDto)
                .toList();
    }

    @Transactional
    @Override
    public AllergenDto getAllergenById(String id) {
        return convertoToDto(this.allergenRepository.findById(id).get());
    }
}
