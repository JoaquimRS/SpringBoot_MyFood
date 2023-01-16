package com.myfood.springboot_myfood.domain.categories.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfood.springboot_myfood.domain.categories.entity.CategoryEntity;



public interface CategoryRepository extends JpaRepository<CategoryEntity, String>{
}
