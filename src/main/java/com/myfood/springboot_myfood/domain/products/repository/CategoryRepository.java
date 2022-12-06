package com.myfood.springboot_myfood.domain.products.repository;

import com.myfood.springboot_myfood.domain.products.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}
