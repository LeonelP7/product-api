package com.example.noBSSpringBoot.product.queryhandlers;

import com.example.noBSSpringBoot.Query;
import com.example.noBSSpringBoot.product.ProductRepository;
import com.example.noBSSpringBoot.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Product> execute(Integer id) {
        Optional product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        return ResponseEntity.ok((Product) product.get());
    }
}
