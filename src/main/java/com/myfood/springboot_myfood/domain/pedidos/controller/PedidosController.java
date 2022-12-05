package com.myfood.springboot_myfood.domain.pedidos.controller;

import com.myfood.springboot_myfood.domain.pedidos.entity.ProductEntity;
import com.myfood.springboot_myfood.domain.pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfood.springboot_myfood.domain.pedidos.dto.ProductoDto;
import com.myfood.springboot_myfood.domain.pedidos.service.ProductoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PedidosController {
    @Autowired
    ProductoService pService;
    @Autowired
    ProductoRepository pRepo;

    @GetMapping("/test")
    public List<ProductEntity> getProducts() {
        return this.pRepo.getProducts();
    }
}
