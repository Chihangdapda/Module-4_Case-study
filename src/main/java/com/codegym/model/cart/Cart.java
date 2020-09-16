package com.codegym.model.cart;

import com.codegym.model.product.Product;

public class Cart {
//    private Product_ProductSize product_productSize;
//    private int quantity;
//    public Cart() {
//    }
//    public Cart(Product_ProductSize product_productSize, int quantity) {
//        this.product_productSize = product_productSize;
//        this.quantity = quantity;
//    }
//    public Product_ProductSize getProduct_productSize() {
//        return product_productSize;
//    }
//    public void setProduct_productSize(Product_ProductSize product_productSize) {
//        this.product_productSize = product_productSize;
//    }
//    public int getQuantity() {
//        return quantity;
//    }
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
        private Product product;
        private int quantity;

        public Cart() {
        }

        public Cart(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }