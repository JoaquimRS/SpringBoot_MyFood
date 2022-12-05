package com.myfood.springboot_myfood.domain.pedidos.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myfood.springboot_myfood.domain.pedidos.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductEntity, Long> {
    @EntityGraph("fetch-products")
    @Query(value = "SELECT p FROM ProductEntity p")
    List<ProductEntity> getProducts();
}
