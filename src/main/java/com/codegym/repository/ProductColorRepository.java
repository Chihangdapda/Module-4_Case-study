package com.codegym.repository;

import com.codegym.model.product.ProductColor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductColorRepository extends PagingAndSortingRepository<ProductColor,Long> {
}
