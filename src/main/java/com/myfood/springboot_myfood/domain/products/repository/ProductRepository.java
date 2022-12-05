package com.myfood.springboot_myfood.domain.products.repository;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @EntityGraph("fetch-products")
    @Query(value = "SELECT p FROM ProductEntity p")
    List<ProductEntity> getProducts();
}
