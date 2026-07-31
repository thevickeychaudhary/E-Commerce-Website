package com.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	// for sorting data
	List<Product> findByNameContainingIgnoreCase(String keyword);

	// for  filtering 
	List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	
	
	// custom query
	@Query("""
			SELECT p
			FROM Product p
			WHERE p.price > :price
			""")
			List<Product> findProductsByPriceGreaterThan(
			        @Param("price") Double price
			);

}
