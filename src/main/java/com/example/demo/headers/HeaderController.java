package com.example.demo.headers;

import com.example.demo.product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/*
This is an implementation of custom headers
 */

@RestController
public class HeaderController {

    @GetMapping(value = "/headers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct(){
        Product product = new Product();
        product.setName("prod1");
        product.setId(1);
        product.setDescription("prod1");

        return ResponseEntity.ok(product);
    }
}
