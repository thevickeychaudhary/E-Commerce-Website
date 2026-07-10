package com.product.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.entity.Product;
import com.product.service.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService prodService;

	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}

	@PostMapping("/add")
	public Product addProduct(@Valid @RequestBody Product prod) {
		return prodService.addProduct(prod);
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return prodService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return prodService.getProductById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		prodService.deleteProduct(id);
		return "Product Delete Successfully";
	}

	@PutMapping("/{id}")
	public Product updateProductById(@PathVariable Long id, @Valid @RequestBody Product prod) {
		return prodService.updateProduct(id, prod);
	}
}
