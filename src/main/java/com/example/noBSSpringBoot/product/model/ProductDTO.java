package com.example.noBSSpringBoot.product.model;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private Double price;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
