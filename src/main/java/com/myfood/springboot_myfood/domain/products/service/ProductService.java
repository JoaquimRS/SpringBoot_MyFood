package com.myfood.springboot_myfood.domain.products.service;

import java.util.List;

import com.myfood.springboot_myfood.domain.products.dto.ProductDto;

public interface ProductService {
    // ProductoDto getProducto(final String slug);

    // List<ProductoDto> feedProducts(final FeedModel feedParams);

    List<ProductDto> getProductos();
}
