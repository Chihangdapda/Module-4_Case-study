package com.codegym.service;

import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void remove(Long id);
}