package com.myfood.springboot_myfood.domain.clients.entity;

import com.myfood.springboot_myfood.domain.common.utils.BaseUtils;
import lombok.*;

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
public class ClientEntity extends BaseUtils {
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

    @Column(name = "avatar")
    private String avatar;

    @Builder
    public ClientEntity(String id_cliente, String nombre, String email, String telefono, String contraseña, String avatar) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.avatar = avatar;
    }
}
