package com.gagan.learn.productservice.service;

import com.gagan.learn.productservice.dto.ProductRequest;
import com.gagan.learn.productservice.dto.ProductResponse;
import com.gagan.learn.productservice.model.Product;
import com.gagan.learn.productservice.repository.ProductRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).map(this::toResponse).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        return toResponse(productRepository.save(product));
    }


    private ProductResponse toResponse(Product product) {
    return new ProductResponse(product.getId(),product.getName(), product.getDescription(), product.getPrice(),product.getCategory());
    }

}
