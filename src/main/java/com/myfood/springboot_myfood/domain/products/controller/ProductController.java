package com.myfood.springboot_myfood.domain.products.controller;

import com.myfood.springboot_myfood.domain.products.dto.AllergenDto;
import com.myfood.springboot_myfood.domain.products.dto.CategoryDto;
import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import com.myfood.springboot_myfood.domain.products.service.AllergenService;
import com.myfood.springboot_myfood.domain.products.service.CategoryService;
import com.myfood.springboot_myfood.domain.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService pService;

    @Autowired
    private AllergenService aService;

    @Autowired
    private CategoryService cService;

    @GetMapping("/")
    public ProductDto.MultipleProductos getProducts() {
        return ProductDto.MultipleProductos
                .builder()
                .productos(pService.getProductos())
                .build();
    }

    @GetMapping("/{id_producto}")
    public ProductDto.SingleProducto getProductoById(@PathVariable String id_producto) {
        return ProductDto.SingleProducto
                .builder()
                .producto(pService.getProductById(id_producto))
                .build();
    }

    @GetMapping("/alergenos")
    public AllergenDto.MultipleAllergens getAllergens() {
        return AllergenDto.MultipleAllergens
                .builder()
                .allergens(aService.getAllergens())
                .build();
    }

    @GetMapping("/alergenos/{id_alergeno}")
    public AllergenDto.SingleAllergen getAlergeno(@PathVariable String id_alergeno) {
        return AllergenDto.SingleAllergen
                .builder()
                .allergen(aService.getAllergenById(id_alergeno))
                .build();
    }

    @GetMapping("/categories")
    public CategoryDto.MultipleCategories getCategories() {
        return CategoryDto.MultipleCategories
                .builder()
                .categories(cService.getCategories())
                .build();
    }

    @GetMapping("/categories/{id_category}")
    public CategoryDto.SingleCategory getCategory(@PathVariable String id_category) {
        return CategoryDto.SingleCategory.
                builder()
                .category(cService.getCategoryById(id_category))
                .build();
    }

}