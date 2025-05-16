package com.gagan.learn.productservice.controller;

import com.gagan.learn.productservice.dto.ProductRequest;
import com.gagan.learn.productservice.dto.ProductResponse;
import com.gagan.learn.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
