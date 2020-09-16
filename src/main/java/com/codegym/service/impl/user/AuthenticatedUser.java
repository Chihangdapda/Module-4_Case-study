package com.codegym.service.impl.user;

import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.*;

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User{

    private static final long serialVersionUID = 1L;
    private User user;

    public AuthenticatedUser(User user)
    {
        super(user.getUserName(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        Set<String> roleAndPermissions = new HashSet<>();
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());

        for (Role role : roles)
        {
            roleAndPermissions.add(role.getName());
        }
        String[] roleNames = new String[roleAndPermissions.size()];
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
        return authorities;
    }
}