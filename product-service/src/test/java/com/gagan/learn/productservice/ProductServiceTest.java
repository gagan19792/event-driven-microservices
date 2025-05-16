package com.gagan.learn.productservice;

import com.gagan.learn.productservice.dto.ProductRequest;
import com.gagan.learn.productservice.model.Product;
import com.gagan.learn.productservice.repository.ProductRepository;
import com.gagan.learn.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Test Product");
        request.setPrice(new BigDecimal("19.99"));
        request.setCategory("Books");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName(request.getName());
        savedProduct.setPrice(request.getPrice());
        savedProduct.setCategory(request.getCategory());

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        var response = productService.createProduct(request);
        assertEquals("Test Product", response.getName());
        assertEquals("Books", response.getCategory());
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Sample");
        product.setPrice(new BigDecimal("29.99"));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals("Sample", result.getName());
    }
}
