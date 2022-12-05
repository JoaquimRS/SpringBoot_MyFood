package com.myfood.springboot_myfood.domain.pedidos.entity;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "productos")
@NamedEntityGraph(name = "fetch-products", attributeNodes = { @NamedAttributeNode("pedidos") })
//@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class),
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })
public class ProductEntity {
    @Id
    private String id_producto;
    @Column(name = "slug")
    private String slug;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private Currency precio;

    @Column(name = "imagen")
    private String imagen;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "productos_pedidos",
            joinColumns = { @JoinColumn(name = "id_producto") }
    )
    private Set<PedidoEntity> pedidos = new HashSet<>();

    @Builder
    public ProductEntity(String id_product, String slug, String nombre, Currency precio, String imagen) {
        this.id_producto = id_product;
        this.slug = slug;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.pedidos = new HashSet<>();
    }
}
