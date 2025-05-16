package com.gagan.learn.productservice.repository;

import com.gagan.learn.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
