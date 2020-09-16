package com.codegym.service.impl.user;

import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import com.codegym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Name " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), passwordEncoder.encode(user.getPassword()),
                getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Role role = user.getRole();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role.getName());
        return authorities;
    }

//    public static void main(String[] args) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pass = encoder.encode("abc123");
//        System.out.printf(pass);
//    }
}