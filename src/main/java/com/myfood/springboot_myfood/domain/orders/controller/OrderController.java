package com.myfood.springboot_myfood.domain.orders.controller;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myfood.springboot_myfood.domain.clients.service.ClientService;
import com.myfood.springboot_myfood.domain.orders.dto.OrderDto;
import com.myfood.springboot_myfood.domain.orders.entity.OrderProductEntity;
import com.myfood.springboot_myfood.domain.orders.service.OrderService;
import com.myfood.springboot_myfood.plugins.IdGenerator;
import com.myfood.springboot_myfood.security.AuthClientDetails;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService oService;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @Autowired
    private ClientService cService;
    
    @GetMapping
    public OrderDto.MultipleOrders getOrders(@AuthenticationPrincipal AuthClientDetails authClientDetails) {
        
        return OrderDto.MultipleOrders
                .builder()
                .orders(oService.getUserOrders(cService.currentUser(authClientDetails).getId_cliente()))
                .build();
    }

    @PostMapping
    public ResponseEntity addOrder(@AuthenticationPrincipal AuthClientDetails authClientDetails, @RequestBody Set<OrderProductEntity> newOrderProducts){
        OrderDto newOrder = new OrderDto();
        newOrder.setId_pedido(IdGenerator.generateWithLength(10));
        newOrder.setId_cliente(cService.currentUser(authClientDetails).getId_cliente());
        newOrder.setEstado("PENDIENTE");
        newOrder.setFecha(LocalDate.now());
        newOrderProducts
            .stream()
            .forEach(o -> o.setId_pedido(newOrder.getId_pedido()));
            
        return this.oService.addOrder(newOrder,newOrderProducts); 
        
    }

    
}
