package com.codegym.controller.user;
import com.codegym.model.cart.Cart;
import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductColor;
import com.codegym.model.product.ProductSize;
import com.codegym.model.user.Role;
import com.codegym.model.user.User;
import com.codegym.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Controller
public class UserController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductColorService productColorService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    //    @Autowired
//    PasswordEncoder passwordEncoder;
    @GetMapping("/register")
    public ModelAndView registerPage(){
        User user = new User();
        ModelAndView mv = new ModelAndView("user/userRegister");
        mv.addObject("user", user);
        return mv;
    }
    @PostMapping("/register")
    public ModelAndView PostRegisterPage(@Validated @ModelAttribute(value = "user") User user, BindingResult bindingResult,@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, HttpSession session ){
//        String password = passwordEncoder.encode(user.getPassword());
//        user.setPassword(password);
//        user.setRoles();
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView model = new ModelAndView("user/userRegister");
            model.addObject("user",user);
            return model;
        }
        user.setRole(roleService.findById(2l));
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("home");
//        modelAndView.addObject("user",user);
        modelAndView.addObject("message","Register Successfully !");
        Page<Product> products;
        Iterable<Category> categories = categoryService.findAll();
        Iterable<ProductSize> productSizes = productSizeService.findAll();
        Iterable<ProductColor> productColors = productColorService.findAll();
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 4 );
        if (s.isPresent()) {
            products = productService.findAllByNameContaining( s.get(), pageable );
        } else {
            products = productService.findAll( pageable );
        }
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        modelAndView.addObject( "products", products );
        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "productSizes", productSizes );
        modelAndView.addObject( "productColors", productColors);
        modelAndView.addObject( "size", cartItems.size() );
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("user",getPrincipal());
        return "adminDashboard";
    }
    @GetMapping(value = {"/home","/admin-home"})
    public String userHome(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, Model model, HttpSession session) {
        Page<Product> products;
        Iterable<Category> categories = categoryService.findAll();
        Iterable<ProductSize> productSizes = productSizeService.findAll();
        Iterable<ProductColor> productColors = productColorService.findAll();
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 4 );
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
        model.addAttribute("user", getPrincipal());
        return "index";
    }
    @GetMapping("/accessDenied")
    public String deniedAccess(){
        return "accessDenied";
    }
    @GetMapping("/admin/user")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s,
                                     @PageableDefault(size = 5) Pageable pageable) {
        Page<User> users;
        if (s.isPresent()) {
            users = userService.findAllByNameContaining(s.get(), pageable);
        } else {
            users = userService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("user/admin/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
    @GetMapping(value = "/admin/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("user/admin/createUser");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @PostMapping("/admin/create")
    public ModelAndView createUser(@Validated @ModelAttribute(value = "user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView model = new ModelAndView("user/admin/createUser");
            model.addObject("user",user);
            return model;
        }
        user.setRole(roleService.findById(2l));
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("user/admin/create");
        modelAndView.addObject("user",user);
        modelAndView.addObject("message","Register Successfully !");
        return modelAndView;
    }
    @ModelAttribute("roles")
    public Iterable<Role> roles(){
        return roleService.findAll();
    }
    @GetMapping("/admin/edit-user/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/admin/edit");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/admin/edit-user")
    public ModelAndView updateCustomer(@ModelAttribute("user") User user){
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/admin/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "user updated successfully");
        return modelAndView;
    }
    @GetMapping("/admin/delete-user/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/admin/delete");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/admin/delete-user")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.remove(user.getUserId());
        return "redirect:/admin/user";
    }
    @GetMapping("/admin/view-user/{id}")
    public ModelAndView viewUser(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/admin/view");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
}