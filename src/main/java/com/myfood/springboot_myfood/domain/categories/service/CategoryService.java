package com.myfood.springboot_myfood.domain.categories.service;

import java.util.List;

import com.myfood.springboot_myfood.domain.categories.dto.CategoryDto;

public interface CategoryService {
    List<CategoryDto> getCategories();

}
