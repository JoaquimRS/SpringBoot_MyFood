package com.myfood.springboot_myfood.domain.categories.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categorias")
@NamedEntityGraph(name = "fetch-categories")
public class CategoryEntity {
    @Id
    private String id_categoria;

    @Column(name = "slug")
    private String slug;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "icono")
    private String icono;
    
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "categorias"
)
    private List<ProductEntity> productos = new ArrayList<>();

    @Builder
    public CategoryEntity(String id_categoria, String slug, String nombre, String icono) {
        this.id_categoria = id_categoria;
        this.slug = slug;
        this.nombre = nombre;
        this.icono = icono;
    }

    public List<ProductEntity> getProducts() {
        return this.productos;
    }

    public void setProducto(List<ProductEntity> productos) {
        this.productos = productos;
    }

}
