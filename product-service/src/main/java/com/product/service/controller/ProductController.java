package com.product.service.controller;

import com.product.service.dto.ProductRequestDto;
import com.product.service.dto.ProductResponseDto;
import com.product.service.dto.ProductSummaryDto;
import com.product.service.repository.ProductRepository;
import com.product.service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService prodService;

    public ProductController(ProductService prodService, ProductRepository productRepository) {
        this.prodService = prodService;
        this.productRepository =  productRepository;
    }

    // DTO PROJECTION
    @GetMapping("/summary")
    public List<ProductSummaryDto> getProductSummary() {
        return prodService.getProductSummary();
    }

    @GetMapping("/search")
    public List<ProductResponseDto> searchProducts(@RequestParam String keyword) {
        return prodService.searchProducts(keyword);
    }

    @GetMapping("/filter/price")
    public List<ProductResponseDto> filterByPrice(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return prodService.filterProductsByPrice(minPrice, maxPrice);
    }

    // for custom query
    @GetMapping("/price")
    public List<ProductResponseDto> getProductByPrice(@RequestParam Double price) {
        System.out.println(">>>>>>>> Inside getProductByPrice");
        return prodService.getProductByPrice(price);
    }

    @PostMapping("/add")
    public ProductResponseDto addProduct(@Valid @RequestBody ProductRequestDto dto) {
        return prodService.addProduct(dto);
    }

    @GetMapping
    public Page<ProductResponseDto> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size, @RequestParam(defaultValue = "id") String sortField, @RequestParam(defaultValue = "asc") String sortDir

    ) {
        System.out.println(">>>>>>>> Inside getAllProducts");

        return prodService.getAllProducts(page, size, sortField, sortDir);
    }

    @GetMapping("/{id:\\d+}")
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

    // JPQL ADAVANCE - COUNT PRODUCTS
    @GetMapping("/count")
    public Long countProducts() {
        return prodService.countProducts();

    }

    // SUM
    @GetMapping("/sum")
    public Double sumOfProductsPrice() {
        return prodService.sumOfProductPrice();
    }

    // AVG
    @GetMapping("/avg")
    public Double getAvgProductPrice() {
        return prodService.getAvgProductPrice();
    }

    // MIN
    @GetMapping("/min")
    public Double getMinProductPrice() {
        return prodService.getMinProductPrice();
    }

    //MAX
    @GetMapping("/max")
    public Double getMaxProductPrice() {
        return prodService.getMaxProductPrice();
    }

    //ORDER BY = ASC
    @GetMapping("/sort/price/asc")
    public List<ProductResponseDto> findProductsOrderByPriceAsc() {
        return prodService.findProductsOrderByPriceAsc();
    }

    //ORDER BY =DESC
    @GetMapping("/sort/price/desc")
    public List<ProductResponseDto> findProductsOrderByPriceDesc() {
        return prodService.findProductsOrderByPriceDesc();
    }

    //GROUP BY
    @GetMapping("/group-by/price")
    public List<Object[]> countProductGroupByPrice() {
        return prodService.countProductGroupByPrice();
    }

    //HAVING
    @GetMapping("/group-by/price/having")
    public List<Object[]> findPriceGroupsWithMoreThanOneProduct() {
        return prodService.findPriceGroupsWithMoreThanOneProduct();
    }

    //Category
    @GetMapping("/category/{categoryName}")
    public List<ProductResponseDto> getProductsByCategoryName(
            @PathVariable String categoryName)  {

        return prodService.getProductsByCategoryName(categoryName);
    }
}
