package com.product.service.service;

import com.product.service.dto.ProductRequestDto;
import com.product.service.dto.ProductResponseDto;
import com.product.service.dto.ProductSummaryDto;
import com.product.service.entity.Category;
import com.product.service.entity.Product;
import com.product.service.exception.ProductNotFoundException;
import com.product.service.repository.CategoryRepository;
import com.product.service.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {

        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    private Product convertToEntity(ProductRequestDto dto) {

        // DTO = Entity
        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Category category = categoryRepo.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found with id " + dto.getCategoryId()));

        product.setCategory(category);

        return product;

    }

    private ProductResponseDto convertToResponse(Product product) {

        // entity to= responsedto

        ProductResponseDto response = new ProductResponseDto();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());

        return response;

    }

    public ProductResponseDto addProduct(ProductRequestDto dto) {

        // DTO = Entity
        Product product = convertToEntity(dto);

        Product savedProduct = productRepo.save(product);

        return convertToResponse(savedProduct);
    }

    public Page<ProductResponseDto> getAllProducts(int page, int size, String sortField, String sortDir) {

//		PageRequest pageable = PageRequest.of(page, size);
//
//		Page<Product> productPage = productRepo.findAll(pageable);
//
//		List<ProductResponseDto> responseList = new ArrayList<>();
//
//		for (Product product : productPage) {
//
////			ProductResponseDto dto = new ProductResponseDto();
////
////			dto.setId(product.getId());
////			dto.setName(product.getName());
////			dto.setDescription(product.getDescription());
////			dto.setPrice(product.getPrice());
////			dto.setQuantity(product.getQuantity());
//
//			responseList.add(convertToResponse(product));
//		}
//
//		return responseList;

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepo.findAll(pageable);

        return productPage.map(this::convertToResponse);

    }

    public List<ProductResponseDto> searchProducts(String keyword) {

        List<Product> products = productRepo.findByNameContainingIgnoreCase(keyword);

        return products.stream().map(this::convertToResponse).toList();
    }

    public List<ProductResponseDto> filterProductsByPrice(Double minPrice, Double maxPrice) {

        List<Product> products = productRepo.findByPriceBetween(minPrice, maxPrice);

        return products.stream().map(this::convertToResponse).toList();
    }

    // for custom Query
    public List<ProductResponseDto> getProductByPrice(Double price) {
        List<Product> products = productRepo.findProductsByPriceGreaterThan(price);

        return products.stream().map(this::convertToResponse).toList();
    }

    public ProductResponseDto getProductById(Long id) {

        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

        return convertToResponse(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto prod) {
        Product updateProd = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));

        updateProd.setName(prod.getName());
        updateProd.setDescription(prod.getDescription());
        updateProd.setPrice(prod.getPrice());
        updateProd.setQuantity(prod.getQuantity());

        return convertToResponse(productRepo.save(updateProd));
    }

    // DTO PROJECTION
    public List<ProductSummaryDto> getProductSummary() {
        return productRepo.findProductSummary();
    }

    // JPQL ADVANCE- Count Products
    // COUNT
    public Long countProducts() {
        return productRepo.countProducts();
    }

    // SUM
    public Double sumOfProductPrice() {
        return productRepo.getTotalSumProductPrice();
    }

    // AVG
    public Double getAvgProductPrice() {
        return productRepo.getAverageProductPrice();
    }

    // MIN
    public Double getMinProductPrice() {
        return productRepo.getMinimumProductPrice();
    }

    // MAX
    public Double getMaxProductPrice() {
        return productRepo.getMaximumProductPrice();
    }

    //ORDER BY = ASC
    public List<ProductResponseDto> findProductsOrderByPriceAsc() {
        List<Product> products = productRepo.findProductsOrderByPriceAsc();
        return products.stream().map(this::convertToResponse).toList();
    }

    // ORDER BY = DESC
    public List<ProductResponseDto> findProductsOrderByPriceDesc() {
        List<Product> products = productRepo.findProductsOrderByPriceDesc();
        return products.stream().map(this::convertToResponse).toList();
    }

    //GROUP BY
    public List<Object[]> countProductGroupByPrice() {
        return productRepo.countProductsGroupByPrice();
    }

    //HAVING
    public List<Object[]> findPriceGroupsWithMoreThanOneProduct() {
        return productRepo.findPriceGroupsWithMoreThanOneProduct();
    }

    // Category - INNER JOIN
    public List<ProductResponseDto> getProductsByCategoryName(String categoryName) {

        List<Product> products = productRepo.findProductsByCategoryName(categoryName);

        return products.stream().map(this::convertToResponse).toList();
    }

    //lEFT JOIN
    public List<ProductResponseDto> getAllProductsWithCategory() {

        List<Product> products = productRepo.findAllProductsWithCategory();

        return products.stream().map(this::convertToResponse).toList();
    }

    //JOIN FETCH
    public List<ProductResponseDto> getAllProductsWithCategoryFetch() {

        List<Product> products = productRepo.findAllProductsWithCategoryFetch();

        return products.stream().map(this::convertToResponse).toList();
    }

}
