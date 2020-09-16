package com.codegym.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotEmpty
    private String productName;

    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @NotEmpty
    private String discount;


    private String productPhoto;

    private Long shopping;


    private Long stock;

    private String color;

    private String size;

    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Product() {
    }

    public Product(Long productId, @NotEmpty String productName, Double productPrice, Producer producer, @NotEmpty String discount, @NotEmpty String productPhoto, @NotEmpty String amount, Long shopping, Long stock, String color, String size, @NotEmpty String description, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.producer = producer;
        this.discount = discount;
        this.productPhoto = productPhoto;

        this.shopping = shopping;
        this.stock = stock;
        this.color = color;
        this.size = size;
        this.description = description;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public Long getShopping() {
        return shopping;
    }

    public void setShopping(Long shopping) {
        this.shopping = shopping;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
