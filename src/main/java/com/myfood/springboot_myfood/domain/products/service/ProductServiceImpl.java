package com.myfood.springboot_myfood.domain.products.service;

import com.myfood.springboot_myfood.domain.products.entity.AllergenEntity;

import org.springframework.stereotype.Service;

import com.myfood.springboot_myfood.domain.categories.entity.CategoryEntity;
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
                .imagen(entity.getImagen())
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
    public List<ProductDto> searchProducts(String producto) {
        return this.productRepository.searchProducts(producto)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ProductDto> getFilteredProducts(List<String> categorias, String orden, List<String> rango, Integer paginacion) {
        paginacion = (paginacion-1) * 12;
        if (categorias.isEmpty()) {
            return this.productRepository.getFilteredProducts(orden, rango.get(0), rango.get(1), paginacion)
                    .stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList());
        }
        return this.productRepository.getFilteredProductsCategories(categorias, orden, rango.get(0), rango.get(1), paginacion)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Integer getFilteredProductsLength(List<String> categorias, List<String> rango) {
        if(categorias.isEmpty()) {
            return this.productRepository.getFilteredProductsLength(rango.get(0), rango.get(1));
        }
        return this.productRepository.getFilteredProductsCategoriesLength(categorias, rango.get(0), rango.get(1));
    }

    @Transactional
    @Override
    public ProductDto getProductById(String slug_producto) {
        return convertEntityToDto(this.productRepository.findBySlug(slug_producto));
    }
}
