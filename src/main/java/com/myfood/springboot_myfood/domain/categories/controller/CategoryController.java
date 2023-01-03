package com.myfood.springboot_myfood.domain.categories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myfood.springboot_myfood.domain.categories.dto.CategoryDto;
import com.myfood.springboot_myfood.domain.categories.service.CategoryService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService cService;

    @GetMapping
    public CategoryDto.MultipleCategories getCategories() {
        return CategoryDto.MultipleCategories
                .builder()
                .categories(cService.getCategories())
                .build();
    }
}
