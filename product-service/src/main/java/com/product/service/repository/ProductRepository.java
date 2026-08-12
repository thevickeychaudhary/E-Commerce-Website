package com.product.service.repository;

import com.product.service.dto.ProductSummaryDto;
import com.product.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // For sorting data
    List<Product> findByNameContainingIgnoreCase(String keyword);

    // For filtering
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Custom query
    @Query("""
            SELECT p
            FROM Product p
            WHERE p.price > :price
            """)
    List<Product> findProductsByPriceGreaterThan(@Param("price") Double price);

    // JPQL Projection or DTO Projection
    @Query("""
            SELECT new com.product.service.dto.ProductSummaryDto( p.name,p.price) FROM Product p
            """)
    List<ProductSummaryDto> findProductSummary();

    // JPQL ADVANCE (AGGEGRATE FUNCTION)
    // COUNT
    @Query("""
            select count(p) from Product p
            """)
    Long countProducts();

    // SUM
    @Query("""
            SELECT SUM(p.price) FROM Product p
            """)
    Double getTotalSumProductPrice();

    // AVG
    @Query("""
            SELECT AVG(p.price) FROM Product p
            """)
    Double getAverageProductPrice();

    // MIN
    @Query("""
            SELECT MIN(p.price) FROM Product p
            """)
    Double getMinimumProductPrice();

    // MAX
    @Query("""
            SELECT MAX(p.price) FROM Product p
            """)
    Double getMaximumProductPrice();

    //ORDER BY = ASC
    @Query("""
            SELECT p FROM Product p
            ORDER BY p.price ASC
            """)
    List<Product> findProductsOrderByPriceAsc();

    //ORDER BY = DESC
    @Query("""
            SELECT p FROM Product p
            ORDER BY p.price DESC
            """)
    List<Product> findProductsOrderByPriceDesc();

    //GROUP BY
    @Query("""
            SELECT p.price, COUNT(p) FROM Product p
            GROUP BY p.price
            """)
    List<Object[]> countProductsGroupByPrice();

    // HAVING
    @Query("""
                       SELECT p.price, Count(p) FROM Product p
                       GROUP BY p.price
                       HAVING COUNT(p) > 1
            """)
    List<Object[]> findPriceGroupsWithMoreThanOneProduct();

    // JOINS
    // JPQL INNER-JOIN
    @Query("""
            SELECT p
            FROM Product p
            JOIN p.category c
            WHERE c.categoryName = :categoryName
            """)
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);

    //LEFT JOIN
    @Query("""
            SELECT p
            FROM Product p
            LEFT JOIN p.category c
            """)
    List<Product> findAllProductsWithCategory();

    //JOIN FETCH
    @Query("""
            SELECT p
            FROM Product p
            JOIN FETCH p.category c
            """)
    List<Product> findAllProductsWithCategoryFetch();
}
