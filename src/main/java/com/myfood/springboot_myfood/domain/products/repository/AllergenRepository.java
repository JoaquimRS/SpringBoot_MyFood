package com.myfood.springboot_myfood.domain.products.repository;

import com.myfood.springboot_myfood.domain.products.entity.AllergenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<AllergenEntity, String> {
}
