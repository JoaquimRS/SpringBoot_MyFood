package com.myfood.springboot_myfood.domain.products.service;

import com.myfood.springboot_myfood.domain.products.dto.AllergenDto;

import java.util.List;

public interface AllergenService {
    List<AllergenDto> getAllergens();

    AllergenDto getAllergenById(String id);
}
