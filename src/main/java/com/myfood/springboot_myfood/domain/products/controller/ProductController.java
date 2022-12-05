package com.myfood.springboot_myfood.domain.products.controller;

import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import com.myfood.springboot_myfood.domain.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService pService;

    @GetMapping("/test")
    public ProductDto.MultipleProductos getProducts() {
        return ProductDto.MultipleProductos.builder().productos(pService.getProductos()).build();
    }

}