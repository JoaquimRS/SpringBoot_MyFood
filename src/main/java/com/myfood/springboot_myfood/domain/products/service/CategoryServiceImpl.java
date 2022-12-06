package com.myfood.springboot_myfood.domain.products.service;

import com.myfood.springboot_myfood.domain.products.dto.CategoryDto;
import com.myfood.springboot_myfood.domain.products.entity.CategoryEntity;
import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;
import com.myfood.springboot_myfood.domain.products.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .findAll()
                .stream()
                .map(this::convertoToDto)
                .toList();
    }

    @Transactional
    @Override
    public CategoryDto getCategoryById(String id) {
        return convertoToDto(this.categoryRepository.findById(id).get());
    }
}
