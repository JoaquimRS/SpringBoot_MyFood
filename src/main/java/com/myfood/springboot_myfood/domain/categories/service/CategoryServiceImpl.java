package com.myfood.springboot_myfood.domain.categories.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfood.springboot_myfood.domain.categories.dto.CategoryDto;
import com.myfood.springboot_myfood.domain.categories.entity.CategoryEntity;
import com.myfood.springboot_myfood.domain.categories.respository.CategoryRepository;
import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private CategoryDto convertoToDto(CategoryEntity entity) {
        return CategoryDto.builder()
                .id_categoria(entity.getId_categoria())
                .slug(entity.getSlug())
                .nombre(entity.getNombre())
                .icono(entity.getIcono())
                .productos(entity.getProductos()
                        .stream()
                        .map(ProductEntity::getId_producto)
                        .toList())
                .build();
    }

    @Transactional
    @Override
    public List<CategoryDto> getCategories() {
        return this.categoryRepository
                .findAll(Sort.by(Sort.Direction.ASC, "nombre"))
                .stream()
                .map(this::convertoToDto)
                .toList();
    }
}
