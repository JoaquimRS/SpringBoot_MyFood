package com.myfood.springboot_myfood.domain.products.controller;

import com.myfood.springboot_myfood.domain.products.dto.AllergenDto;
import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import com.myfood.springboot_myfood.domain.products.service.AllergenService;

import com.myfood.springboot_myfood.domain.products.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService pService;

    @Autowired
    private AllergenService aService;

    @GetMapping
    public ProductDto.MultipleProductos getProducts() {
        return ProductDto.MultipleProductos
                .builder()
                .productos(pService.getProductos())
                .build();
    }

    @GetMapping("/filtro")
    public ProductDto.MultipleProductos getFilteredProducts(@RequestParam List<String> categorias, @RequestParam String orden, @RequestParam List<String> rango) {
        return ProductDto.MultipleProductos
                .builder()
                .productos(pService.getFilteredProducts(categorias,orden,rango))
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

}