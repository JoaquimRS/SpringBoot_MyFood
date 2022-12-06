package com.myfood.springboot_myfood.domain.orders.service;

import com.myfood.springboot_myfood.domain.orders.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getUserOrders();
}
