package com.codegym.repository;

import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    Page<User> findAllByUserNameContaining(String userName, Pageable pageable);
//    Page<User> findAllByRole(Role role, Pageable pageable);

}
