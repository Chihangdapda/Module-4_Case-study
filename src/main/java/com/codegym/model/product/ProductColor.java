package com.codegym.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productcolors")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colorId;
    @NotEmpty
    private String color;

    public ProductColor() {
    }

    public ProductColor(Long colorId, @NotEmpty String color) {
        this.colorId = colorId;
        this.color = color;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
