package com.codegym.service.impl.user;

import com.codegym.model.product.Product;
import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import com.codegym.repository.UserRepository;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Page<User> findAllByNameContaining(String name, Pageable pageable) {
        return userRepository.findAllByUserNameContaining(name,pageable);
    }

    @Override
    public Page<User> findAllByRole(Role role, Pageable pageable) {
//        return userRepository.findAllByRole(role,pageable);
        return null;
    }

}
