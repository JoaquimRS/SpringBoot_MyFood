package com.myfood.springboot_myfood.domain.products.service;

import java.util.List;

import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    List<ProductDto> getProductos();
    
    List<ProductDto> getFilteredProducts(List<String> categorias, String orden, List<String> rango, Integer paginacion);

    List<ProductDto> searchProducts(String producto);

    Integer getFilteredProductsLength(List<String> categorias, List<String> rango);

    ProductDto getProductBySlug(String slug_producto);

    ProductDto getProductById(String id_producto);

}
