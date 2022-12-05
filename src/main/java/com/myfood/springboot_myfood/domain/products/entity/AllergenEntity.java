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
@Enabled
@Table(name = "alergenos")
@NamedEntityGraph(name = "fetch-allergens")
public class AllergenEntity {
    @Id
    private String id_alergeno;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "alergenos"
    )
    private List<ProductEntity> productos = new ArrayList<>();

    @Builder
    public AllergenEntity(String id_alergeno, String nombre, String imagen) {
        this.id_alergeno = id_alergeno;
        this.nombre = nombre;
        this.imagen = imagen;
        this.productos = new ArrayList<>();
    }

    public List<ProductEntity> getProductos() { return this.productos; }

    public void setProductos(List<ProductEntity> productos) { this.productos = productos; }
}
