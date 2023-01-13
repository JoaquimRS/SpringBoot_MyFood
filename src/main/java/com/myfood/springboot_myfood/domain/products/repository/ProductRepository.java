package com.myfood.springboot_myfood.domain.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @EntityGraph("fetch-products")
    @Query(value = "SELECT p FROM ProductEntity p")
    List<ProductEntity> getProducts();

    @Query(
        value = "SELECT p.* FROM productos p " +
                "WHERE p.nombre LIKE %:producto% ",
        nativeQuery = true)
    public List<ProductEntity> searchProducts(@Param("producto") String producto);
    
    @Query(
        value = "SELECT p.* FROM productos p " + 
                "INNER JOIN categorias_productos c " + 
                "ON c.id_producto = p.id_producto " +
                "WHERE p.precio BETWEEN :rango1 AND :rango2 " +
                "ORDER BY CASE WHEN :orden = 'ASC' THEN p.precio END ASC, " + 
                "CASE WHEN :orden = 'DESC' THEN p.precio END DESC " +
                "LIMIT :paginacion,12",
        nativeQuery = true)
    public List<ProductEntity> getFilteredProducts(@Param("orden") String orden, @Param("rango1") String rango1, @Param("rango2") String rango2, @Param("paginacion") Integer paginacion);
    
    @Query(
        value = "SELECT p.* FROM productos p " + 
                "INNER JOIN categorias_productos c " + 
                "ON c.id_producto = p.id_producto " +
                "WHERE c.id_categoria IN(:categorias) " +
                "AND p.precio BETWEEN :rango1 AND :rango2 " +
                "ORDER BY CASE WHEN :orden = 'ASC' THEN p.precio END ASC, " + 
                "CASE WHEN :orden = 'DESC' THEN p.precio END DESC " +
                "LIMIT :paginacion,12",
        nativeQuery = true)
    public List<ProductEntity> getFilteredProductsCategories(@Param("categorias") List<String> categorias, @Param("orden") String orden, @Param("rango1") String rango1, @Param("rango2") String rango2, @Param("paginacion") Integer paginacion);

    @Query(
        value = "SELECT COUNT(*) FROM productos p " + 
                "INNER JOIN categorias_productos c " + 
                "ON c.id_producto = p.id_producto " +
                "WHERE p.precio BETWEEN :rango1 AND :rango2 ",
        nativeQuery = true)
    public Integer getFilteredProductsLength(@Param("rango1") String rango1, @Param("rango2") String rango2);

    @Query(
        value = "SELECT COUNT(*) FROM productos p " + 
                "INNER JOIN categorias_productos c " + 
                "ON c.id_producto = p.id_producto " +
                "WHERE c.id_categoria IN(:categorias) " +
                "AND p.precio BETWEEN :rango1 AND :rango2 ",
        nativeQuery = true)
    public Integer getFilteredProductsCategoriesLength(@Param("categorias") List<String> categorias, @Param("rango1") String rango1, @Param("rango2") String rango2);


    @Query(value = "SELECT * FROM productos p WHERE p.slug = :slug",nativeQuery = true)
    public ProductEntity findBySlug(@Param("slug") String slug);

    
}
