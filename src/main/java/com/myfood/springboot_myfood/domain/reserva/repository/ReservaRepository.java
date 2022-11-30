package com.myfood.springboot_myfood.domain.reserva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myfood.springboot_myfood.domain.reserva.model.ReservaModel;

public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    @Query(value = "SELECT fecha FROM festivos", nativeQuery = true)
    public List<?> getHolidays();
    
    @Query(value = "SELECT r.fecha FROM reservas r GROUP BY r.fecha HAVING (SUM(r.n_comensales) + :comensales) > 50;", nativeQuery = true)
    List<?> getBannedDays(@Param("comensales") Integer comensales);
}
