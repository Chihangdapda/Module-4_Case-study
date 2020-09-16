package com.codegym.service.impl.product;

import com.codegym.model.product.Producer;
import com.codegym.repository.ProducerRepository;
import com.codegym.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public Iterable<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producer findById(Long id) {
        return producerRepository.findOne(id);
    }

    @Override
    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void remove(Long id) {
        producerRepository.delete(id);
    }
}
