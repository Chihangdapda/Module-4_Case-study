package com.codegym.service.impl.product;

import com.codegym.model.product.Product;
import com.codegym.model.product.ProductSize;
import com.codegym.repository.ProductSizeRepository;
import com.codegym.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductSizeServiceImpl implements ProductSizeService {
    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public ProductSize findById(Long id) {
        return productSizeRepository.findOne( id );
    }

    @Override
    public Iterable<ProductSize> findAll() {
        return productSizeRepository.findAll();
    }

}
