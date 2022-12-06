package com.myfood.springboot_myfood.domain.orders.service;

import com.myfood.springboot_myfood.domain.orders.dto.OrderDto;
import com.myfood.springboot_myfood.domain.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Transactional
    @Override
    public List<OrderDto> getUserOrders() {
        return new ArrayList<>();
    }
}
