package com.codegym.configuration;

import com.codegym.service.*;

import com.codegym.service.impl.*;
import com.codegym.service.impl.product.CategoryServiceImpl;
import com.codegym.service.impl.product.ProducerServiceImpl;
import com.codegym.service.impl.product.ProductColorServiceImpl;
//import com.codegym.service.impl.ProductServiceImpl;
import com.codegym.service.impl.product.ProductSizeServiceImpl;
import com.codegym.service.impl.product.ProductServiceImpl;
import com.codegym.service.impl.user.RoleServiceImpl;
import com.codegym.service.impl.user.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.codegym")
@EnableJpaRepositories("com.codegym.repository")
@PropertySource("classpath:application.properties")
@EnableSpringDataWebSupport
public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    @Resource
    public Environment env;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //Thymeleaf Configuration
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setApplicationContext( applicationContext );
        templateResolver.setPrefix( "/WEB-INF/views/" );
        templateResolver.setSuffix( ".html" );
        templateResolver.setCharacterEncoding( "UTF-8" );
        templateResolver.setTemplateMode( TemplateMode.HTML );

        return templateResolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver( templateResolver() );
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine( templateEngine() );
        return viewResolver;
    }

    //JPA Configuration
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource() );
        em.setPackagesToScan( new String[]{"com.codegym.model"} );

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter( vendorAdapter );
        em.setJpaProperties( additionalProperties() );
        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty( "hibernate.hbm2ddl.auto", "update" );
        properties.setProperty( "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect" );
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( env.getProperty( "driverClassName" ) );
        dataSource.setUrl( env.getProperty( "jdbcUrl" ) );
        dataSource.setUsername( env.getProperty( "jdbcUsername" ) );
        dataSource.setPassword( env.getProperty( "jdbcPassword" ) );
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( emf );
        return transactionManager;
    }

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/**" )
                .addResourceLocations( CLASSPATH_RESOURCE_LOCATIONS );
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
    @Bean
    public CategoryService CategoryService() {
        return new CategoryServiceImpl();
    }
    @Bean
    public ProductSizeService ProductSizeService() {
        return new ProductSizeServiceImpl();
    }
    @Bean
    public ProductColorService ProductColorService() {
        return new ProductColorServiceImpl();
    }
    @Bean
    public ProducerService ProducerService() {
        return new ProducerServiceImpl();
    }
    @Bean
    public UserService userService(){return new UserServiceImpl(); }
    @Bean
    public RoleService roleService(){
        return new RoleServiceImpl();
    }

    // security
    @Bean
    public SpringSecurityDialect securityDialect(){
        return new SpringSecurityDialect();
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("ValidationMessages");
        return messageSource;
    }

}
