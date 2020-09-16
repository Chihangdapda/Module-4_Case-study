package com.codegym.repository;

import com.codegym.model.product.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {

}
