package com.example.noBSSpringBoot.product.queryhandlers;

import com.example.noBSSpringBoot.Query;
import com.example.noBSSpringBoot.product.ProductRepository;
import com.example.noBSSpringBoot.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<Product>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> execute(Void input) {
        return ResponseEntity.ok(productRepository.findAll());
    }
}
