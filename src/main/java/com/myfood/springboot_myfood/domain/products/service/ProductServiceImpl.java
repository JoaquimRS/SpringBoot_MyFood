package com.myfood.springboot_myfood.domain.products.service;

import com.myfood.springboot_myfood.domain.products.entity.AllergenEntity;
import com.myfood.springboot_myfood.domain.products.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;
import com.myfood.springboot_myfood.domain.products.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private ProductDto convertEntityToDto(ProductEntity entity) {
        return ProductDto.builder()
                .id_producto(entity.getId_producto())
                .icono(entity.geticono())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .categorias(entity.getCategorias()
                        .stream()
                        .map(CategoryEntity::getId_categoria)
                        .collect(Collectors.toList()))
                .alergenos(entity.getAlergenos()
                        .stream()
                        .map(AllergenEntity::getId_alergeno)
                        .collect(Collectors.toList()))
                .slug(entity.getSlug())
                .build();
    }

    @Transactional
    @Override
    public List<ProductDto> getProductos() {
        return this.productRepository.getProducts()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductDto getProductById(String id) {
        return convertEntityToDto(this.productRepository.findById(id).get());
    }
}
