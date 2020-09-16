package com.codegym.service;

import com.codegym.model.product.Category;

public interface CategoryService {

    Category findById(Long id);

    Iterable<Category> findAll();

    void save(Category category);

    void remove(Long id);
}
