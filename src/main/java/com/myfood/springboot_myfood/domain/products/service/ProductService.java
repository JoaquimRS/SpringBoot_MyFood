package com.myfood.springboot_myfood.domain.products.service;

import java.util.List;

import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    List<ProductDto> getProductos();

    ProductDto getProductById(String id);
}
