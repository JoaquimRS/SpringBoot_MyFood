package com.myfood.springboot_myfood.domain.pedidos.service;

import org.springframework.stereotype.Service;

import com.myfood.springboot_myfood.domain.pedidos.dto.ProductoDto;
import com.myfood.springboot_myfood.domain.pedidos.entity.ProductEntity;
import com.myfood.springboot_myfood.domain.pedidos.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    private ProductoDto convertEntityToDto(ProductEntity entity) {
        return ProductoDto.builder()
                .id_producto(entity.getId_producto())
                .slug(entity.getSlug())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .imagen(entity.getImagen())
                .build();
    }

    @Transactional
    @Override
    public List<ProductoDto> getProductos() {
        System.out.println("----------------------------- HERE -----------------------------");
        System.out.println(this.productoRepository.getProducts());

        return new ArrayList<>();
    }
}
