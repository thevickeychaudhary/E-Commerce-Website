package com.product.service.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.dto.ProductRequestDto;
import com.product.service.dto.ProductResponseDto;
import com.product.service.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService prodService;

	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}

	
	@GetMapping("/search") 
	public List<ProductResponseDto> searchProducts(@RequestParam String keyword)
	{
		return prodService.searchProducts(keyword);
	}
	
	
	@PostMapping("/add")
	public ProductResponseDto addProduct(@Valid @RequestBody ProductRequestDto dto) {
		return prodService.addProduct(dto);
	}

	@GetMapping
	public Page<ProductResponseDto> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDir
			
			
			
			) {
		return prodService.getAllProducts(page, size, sortField,sortDir);
	}

	@GetMapping("/{id}")
	public ProductResponseDto getById(@PathVariable Long id) {
		return prodService.getProductById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		prodService.deleteProduct(id);
		return "Product Delete Successfully";
	}

	@PutMapping("/{id}")
	public ProductResponseDto updateProductById(@PathVariable Long id, @Valid @RequestBody ProductRequestDto prod) {
		return prodService.updateProduct(id, prod);
	}
}
