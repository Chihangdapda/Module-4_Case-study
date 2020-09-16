package com.codegym.service;

import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void save(User user);

    void remove(Long id);

    Page<User> findAllByNameContaining(String name, Pageable pageable);

    Page<User> findAllByRole(Role role, Pageable pageable);


}
