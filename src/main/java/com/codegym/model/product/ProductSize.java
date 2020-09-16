package com.codegym.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productsizes")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;
    @NotEmpty
    private String sizeName;

    public ProductSize() {
    }

    public ProductSize(Long sizeId, @NotEmpty String sizeName) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}
