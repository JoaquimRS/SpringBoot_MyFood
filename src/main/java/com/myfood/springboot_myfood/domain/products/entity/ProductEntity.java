package com.myfood.springboot_myfood.domain.products.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.myfood.springboot_myfood.domain.categories.entity.CategoryEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "productos")
@NamedEntityGraph(name = "fetch-products")
public class ProductEntity {
    @Id
    private String id_producto;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "slug")
    private String slug;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "categorias_productos",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<CategoryEntity> categorias = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "productos_alergenos",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_alergeno")
    )
    private List<AllergenEntity> alergenos = new ArrayList<>();

    @Builder
    public ProductEntity(String id_product, String imagen, String nombre, BigDecimal precio, String slug) {
        this.id_producto = id_product;
        this.slug = slug;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.categorias = new ArrayList<>();
        this.alergenos = new ArrayList<>();
    }

    public List<CategoryEntity> getCategories() {
        return this.categorias;
    }

    public void addCategory(CategoryEntity category) {
        this.categorias.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(String cateogyId) {
        CategoryEntity cat = this.categorias
                .stream()
                .filter(c -> c.getId_categoria() == cateogyId)
                .findFirst()
                .orElse(null);

        if (cat != null) {
            this.categorias.remove(cat);
            cat.getProducts().remove(this);
        }
    }

    public List<AllergenEntity> getAllergens() {
        return this.alergenos;
    }

    public void addCategory(AllergenEntity allergen) {
        this.alergenos.add(allergen);
        allergen.getProductos().add(this);
    }

    public void removeAllergen(String allergenId) {
        AllergenEntity allergen = this.alergenos
                .stream()
                .filter(a -> a.getId_alergeno() == allergenId)
                .findFirst()
                .orElse(null);

        if (allergen != null) {
            this.alergenos.remove(allergen);
            allergen.getProductos().remove(this);
        }
    }
}
