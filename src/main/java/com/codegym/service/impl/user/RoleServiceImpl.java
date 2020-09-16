package com.codegym.service.impl.user;

import com.codegym.model.user.Role;
import com.codegym.repository.RoleRepository;
import com.codegym.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepository.delete(id);
    }
}