package com.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameContainingIgnoreCase(String keyword);
	

}
