package com.codegym.model.orderAndTransaction;

import com.codegym.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Long detailId, Order order, Product product) {
        this.detailId = detailId;
        this.order = order;
        this.product = product;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
