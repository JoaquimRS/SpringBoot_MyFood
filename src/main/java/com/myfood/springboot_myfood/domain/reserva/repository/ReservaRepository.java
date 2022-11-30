package com.myfood.springboot_myfood.domain.reserva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myfood.springboot_myfood.domain.reserva.model.ReservaModel;

public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    @Query(value = "SELECT fecha FROM festivos", nativeQuery = true)
    public List<?> getHolidays();
}
