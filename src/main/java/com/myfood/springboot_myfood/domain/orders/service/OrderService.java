package com.myfood.springboot_myfood.domain.orders.service;

import com.myfood.springboot_myfood.domain.orders.dto.OrderDto;
import com.myfood.springboot_myfood.domain.orders.dto.OrderProductDto;
import com.myfood.springboot_myfood.domain.orders.entity.OrderEntity;
import com.myfood.springboot_myfood.domain.orders.entity.OrderProductEntity;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<OrderDto> getUserOrders(String client);
    ResponseEntity addOrder(OrderDto order,Set<OrderProductEntity> orderProducts);
}
