package com.myfood.springboot_myfood.domain.reserva.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myfood.springboot_myfood.domain.reserva.model.ReservaModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    @Query(value = "SELECT fecha FROM festivos", nativeQuery = true)
    public List<?> getHolidays();

    @Query(value = "SELECT r.fecha FROM reservas r GROUP BY r.fecha, r.tipo HAVING (SUM(r.n_comensales) + :comensales) > 50 AND r.tipo = :servicio", nativeQuery = true)
    List<?> getBannedDays(@Param("comensales") Integer comensales, @Param("servicio") String servicio);

    @Query(value = "SELECT * FROM reservas r WHERE id_reserva = :reserva", nativeQuery = true)
    ReservaModel getReserva(@Param("reserva") String reserva);

    @Query(value = "INSERT INTO reservas VALUES (:obj_reserva)", nativeQuery = true)
    void saveReserve(@Param("obj_reserva") ReservaModel obj_reserva);

    @Query(value = "SELECT id_cliente, fecha, tipo FROM reservas WHERE tipo = :servicio AND id_cliente = :id_cli AND fecha = :f_reserva", nativeQuery = true)
    <T> List<T> checkReserve(@Param("id_cli") String id_cli, @Param("servicio") String servicio, @Param("f_reserva") Date f_resreva);
    
}
