package com.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.service.dto.ProductSummaryDto;
import com.product.service.entity.Product;

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

}
