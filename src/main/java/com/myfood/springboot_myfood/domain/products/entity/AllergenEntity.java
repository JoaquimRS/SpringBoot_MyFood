package com.myfood.springboot_myfood.domain.products.entity;

import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alergenos")
@NamedEntityGraph(name = "fetch-allergens")
public class AllergenEntity {
    @Id
    private String id_alergeno;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "icono")
    private String icono;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "alergenos"
    )
    private List<ProductEntity> productos = new ArrayList<>();

    @Builder
    public AllergenEntity(String id_alergeno, String nombre, String icono) {
        this.id_alergeno = id_alergeno;
        this.nombre = nombre;
        this.icono = icono;
        this.productos = new ArrayList<>();
    }

    public List<ProductEntity> getProductos() { return this.productos; }

    public void setProductos(List<ProductEntity> productos) { this.productos = productos; }
}
