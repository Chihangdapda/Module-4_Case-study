package com.codegym.formatter;

import com.codegym.model.product.Producer;
import com.codegym.model.user.Role;
import com.codegym.service.ProducerService;
import com.codegym.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {
    private RoleService roleService;

    @Autowired
    public RoleFormatter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        return roleService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Role object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}