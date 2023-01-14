package com.myfood.springboot_myfood.domain.orders.repository;

import com.myfood.springboot_myfood.domain.orders.entity.OrderEntity;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @EntityGraph("fetch-orders")
    @Query("SELECT o FROM OrderEntity o WHERE o.id_cliente = :id_cliente")
    List<OrderEntity> getOrders(@Param("id_cliente") String id_cliente);
}
