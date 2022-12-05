package com.myfood.springboot_myfood.domain.pedidos.service;

import java.util.List;

import com.myfood.springboot_myfood.domain.pedidos.dto.ProductoDto;
import com.myfood.springboot_myfood.domain.pedidos.entity.ProductEntity;
import org.springframework.stereotype.Service;
public interface ProductoService {
    // ProductoDto getProducto(final String slug);

    // List<ProductoDto> feedProducts(final FeedModel feedParams);

    List<ProductoDto> getProductos();
}
