package com.codegym.model.orderAndTransaction;

import com.codegym.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @NotEmpty
    private String orderDate;

    public Order() {
    }

    public Order(Long orderId, User user, @NotEmpty String orderDate) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
