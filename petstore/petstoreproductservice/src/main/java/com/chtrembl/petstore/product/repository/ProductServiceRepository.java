package com.chtrembl.petstore.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chtrembl.petstore.product.model.Product;

@Repository
public interface ProductServiceRepository extends JpaRepository<Product, Long> {
    // Define custom queries or use Spring Data methods
}
