package com.codegym.service;

import com.codegym.model.product.Producer;

public interface ProducerService {
    Iterable<Producer> findAll();

    Producer findById(Long id);

    void save(Producer producer);

    void remove(Long id);
}
