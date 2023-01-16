package com.myfood.springboot_myfood.domain.clients.repository;

import com.myfood.springboot_myfood.domain.clients.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByNombre(String nombre);

    @Override
    @Query("SELECT c FROM ClientEntity c WHERE c.id_cliente = :id_cliente")
    Optional<ClientEntity> findById(@Param("id_cliente") String id_cliente);

    Optional<ClientEntity> findByEmail(String email);
    @Query("SELECT c FROM ClientEntity c WHERE c.nombre = :nombre OR c.email = :email")
    List<ClientEntity> findByNombreOrEmail(@Param("nombre") String nombre, @Param("email") String email);

}
