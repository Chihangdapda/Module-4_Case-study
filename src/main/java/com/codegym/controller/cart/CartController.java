package com.codegym.controller.cart;

import com.codegym.model.cart.Cart;
import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.service.CategoryService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
//    @Autowired
//    private ProductSizeService productSizeService;
//    @Autowired
//    private ProductColorService productColorService;

    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String viewCart( HttpSession session, Model model) {
        Iterable<Category> categories = categoryService.findAll();
//        Iterable<ProductSize> productSizes = productSizeService.findAll();
//        Iterable<ProductColor> productColors = productColorService.findAll();
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        model.addAttribute( "categories", categories );
        model.addAttribute( "size", cartItems.size() );
        session.setAttribute( "myCartItems", cartItems );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );
        return "/cart";
    }

    public double totalPrice(HashMap<Long, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
//            count += list.getValue().getProduct().getProductPrice() * list.getValue().getQuantity();
        }
        return count;
    }

}
