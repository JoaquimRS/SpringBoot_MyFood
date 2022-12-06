package com.myfood.springboot_myfood.domain.orders.repository;

import com.myfood.springboot_myfood.domain.orders.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
