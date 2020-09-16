package com.codegym.service;


import com.codegym.model.product.ProductColor;

public interface ProductColorService {
    ProductColor findById(Long id);
    Iterable<ProductColor> findAll();
}
