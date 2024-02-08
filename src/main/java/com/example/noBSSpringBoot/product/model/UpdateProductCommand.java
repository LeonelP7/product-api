package com.example.noBSSpringBoot.product.model;

import lombok.Data;

@Data
public class UpdateProductCommand {

    private Integer id;

    private Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }
}
