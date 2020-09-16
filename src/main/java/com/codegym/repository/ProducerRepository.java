package com.codegym.repository;

import com.codegym.model.product.Producer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProducerRepository extends PagingAndSortingRepository<Producer, Long> {
}

