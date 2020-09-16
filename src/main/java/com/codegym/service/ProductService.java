package com.codegym.service;

import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByCategory(Category category,Pageable pageable);

    Page<Product> findAllByProductNameContaining(String s, Pageable pageable);

    Page<Product> findAllByCategory(Category category);

}
