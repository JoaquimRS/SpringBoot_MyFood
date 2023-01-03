package com.myfood.springboot_myfood.domain.clients.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "clientes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nombre"),
                @UniqueConstraint(columnNames = "email")
    })
@NamedEntityGraph(name = "fetch-clients")
public class ClientEntity {
    @Id
    private String id_cliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "contraseña")
    private String contraseña;

    @Builder
    public ClientEntity(String id_cliente, String nombre, String email, String telefono, String contraseña) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }
}
