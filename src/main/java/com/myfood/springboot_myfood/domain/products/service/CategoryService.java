package com.myfood.springboot_myfood.domain.products.service;

import com.myfood.springboot_myfood.domain.products.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(String id);
}
