package com.codegym.controller.product;

import com.codegym.model.product.*;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductColorService productColorService;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private ProductSizeService productSizeService;

    @ModelAttribute("categorys")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("producers")
    public Iterable<Producer> producers() {
        return producerService.findAll();
    }

    @ModelAttribute("productColors")
    public Iterable<ProductColor> productColors() {
        return productColorService.findAll();
    }

    @ModelAttribute("productSizes")
    public Iterable<ProductSize> productSizes(){
        return productSizeService.findAll();
    }

    @GetMapping("/products")
        public ModelAndView listCustomers(@RequestParam("s") Optional<String> s,
                                           @RequestParam("page") Optional<String> page) {
            Page<Product> products;
            int t = 0;
            if (page.isPresent()) {
                t = Integer.parseInt( page.get() );
            }
            Pageable pageable = new PageRequest( t, 8 );
            if (s.isPresent()) {
                products = productService.findAllByProductNameContaining( s.get(), pageable );
            } else {
                products = productService.findAll( pageable );
            }
            ModelAndView modelAndView = new ModelAndView( "/product/list" );
            modelAndView.addObject( "products", products );
            return modelAndView;

    }


    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());

        return modelAndView;
    }


    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/product/create");
            modelAndView.addObject("message", "Invalid information");
            return modelAndView;
        }

        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }


    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getProductId());
        return "redirect:products";
    }

    @GetMapping("/view-product/{id}")
    public ModelAndView viewproduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/view");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }


}
