package com.product.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.service.entity.Product;
import com.product.service.exception.ProductNotFoundException;
import com.product.service.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepo;

	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	public Product addProduct(Product prod) {
		return productRepo.save(prod);
	}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(Long id) {
		return productRepo.findById(id).orElseThrow( () -> new ProductNotFoundException("Product not found with id "+id));
	}

	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}

	public Product updateProduct(Long id, Product prod) {
		Product updateProd = productRepo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));

		updateProd.setName(prod.getName());
		updateProd.setDescription(prod.getDescription());
		updateProd.setPrice(prod.getPrice());
		updateProd.setQuantity(prod.getQuantity());
		return productRepo.save(updateProd);
	}

}
