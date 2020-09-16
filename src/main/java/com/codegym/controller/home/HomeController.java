package com.codegym.controller.home;

import com.codegym.model.cart.Cart;
import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductColor;
import com.codegym.model.product.ProductSize;
import com.codegym.service.CategoryService;
import com.codegym.service.ProductColorService;
import com.codegym.service.ProductService;
import com.codegym.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductColorService productColorService;


    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, Model model, HttpSession session) {
        Page<Product> products;
        Iterable<Category> categories = categoryService.findAll();
        Iterable<ProductSize> productSizes = productSizeService.findAll();
        Iterable<ProductColor> productColors = productColorService.findAll();
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 8 );
        if (s.isPresent()) {
            products = productService.findAllByNameContaining( s.get(), pageable );
        } else {
            products = productService.findAll( pageable );
        }
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        model.addAttribute( "products", products );
        model.addAttribute( "categories", categories );
        model.addAttribute( "productSizes", productSizes );
        model.addAttribute( "productColors", productColors);
        model.addAttribute( "size", cartItems.size() );
        return "home";
    }
    @GetMapping("category/{categoryId}")
    public ModelAndView viewCategory(@PathVariable("categoryId") Long id, Pageable pageable, @RequestParam("page") Optional<String> page) {
        Iterable<Category> categories = categoryService.findAll();
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 8 );
        Category category = categoryService.findById( id );
        Page<Product> products = productService.findAllByCategory( category, pageable );
        ModelAndView modelAndView = new ModelAndView( "categoryView" );
        modelAndView.addObject( "products", products );
        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "category", category );
        return modelAndView;
    }
    @GetMapping("/product-details/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        Iterable<Category> categories = categoryService.findAll();
        Product product = productService.findById( id );
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        model.addAttribute("categories", categories);
        model.addAttribute( "productDetail", product );
        model.addAttribute( "size", cartItems.size() );
        return "product_details";
    }

    @RequestMapping(value = "/addCart/{productId}", method = RequestMethod.GET)
    public String viewCart(ModelMap mm, HttpSession session, @PathVariable("productId") Long productId, Model model) {
        Iterable<Category> categories = categoryService.findAll();
        Product product = productService.findById( productId );
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        Product product1 = productService.findById( productId );
        if (product1 != null) {
            Cart cart;
            if (cartItems.containsKey( productId )) {
                cart = cartItems.get( productId );
                cart.setProduct( product1 );
                cart.setQuantity( cart.getQuantity() + 1 );
            } else {
                cart = new Cart();
                cart.setProduct( product1 );
                cart.setQuantity( 1 );
            }
            cartItems.put( productId, cart );
        }
        model.addAttribute( "productDetail", product );
        model.addAttribute( "categories", categories );
        model.addAttribute( "size", cartItems.size() );
        session.setAttribute( "myCartItems", cartItems );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );
        return "product_details";
    }
    public double totalPrice(HashMap<Long, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getProductPrice() * list.getValue().getQuantity();
        }
        return count;
    }
    @GetMapping("blog")
    public String blog() {
        return "blog";
    }
    @GetMapping("shop")
    public String shop() {
        return "shop";
    }
    @GetMapping("blog/details")
    public String blogDetail() {
        return "blog_details";
    }
    @GetMapping("about")
    public String about() {
        return "about";
    }
    @GetMapping("contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("elements")
    public String elements() {
        return "elements";
    }
    @GetMapping("main")
    public String main() {
        return "main";
    }
}