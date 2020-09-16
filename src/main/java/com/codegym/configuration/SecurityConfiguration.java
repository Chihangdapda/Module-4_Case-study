package com.codegym.configuration;

import com.codegym.configuration.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/home").access("hasRole('USER')")
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
//                .and().formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .successHandler(customSuccessHandler)
//                .usernameParameter("ssoId").passwordParameter("password")
//                .and().csrf()
//                .and().exceptionHandling().accessDeniedPage("/accessDenied");
//    }
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/product-details/**","/home").access("hasRole('USER')")
            .antMatchers("/admin/**").access("hasRole('ADMIN')")
//            .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
            .and().formLogin().successHandler(customSuccessHandler)
            .usernameParameter("ssoId").passwordParameter("password")
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and().csrf()
            .and().exceptionHandling().accessDeniedPage("/accessDenied");
}

    PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}
