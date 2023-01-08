package com.myfood.springboot_myfood.domain.orders.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.myfood.springboot_myfood.domain.orders.dto.OrderDto;
import com.myfood.springboot_myfood.domain.orders.dto.OrderProductDto;
import com.myfood.springboot_myfood.domain.orders.entity.OrderEntity;
import com.myfood.springboot_myfood.domain.orders.entity.OrderProductEntity;
import com.myfood.springboot_myfood.domain.orders.service.OrderService;
import com.myfood.springboot_myfood.domain.products.dto.ProductDto;
import com.myfood.springboot_myfood.plugins.IdGenerator;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService oService;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    
    @GetMapping
    public OrderDto.MultipleOrders getOrders() {
        return OrderDto.MultipleOrders
                .builder()
                .orders(oService.getUserOrders())
                .build();
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody Set<OrderProductEntity> newOrderProducts){
        OrderDto newOrder = new OrderDto();
        newOrder.setId_pedido(IdGenerator.generateWithLength(10));
        newOrder.setId_cliente("asdf");
        newOrder.setEstado("PENDIENTE");
        newOrder.setFecha(LocalDate.now());
        newOrderProducts
            .stream()
            .forEach(o -> o.setId_pedido(newOrder.getId_pedido()));
            
        return this.oService.addOrder(newOrder,newOrderProducts); 
        
    }

    
}
