package com.example.noBSSpringBoot.product.queryhandlers;

import com.example.noBSSpringBoot.Query;
import com.example.noBSSpringBoot.product.ProductRepository;
import com.example.noBSSpringBoot.product.model.Product;
import com.example.noBSSpringBoot.product.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {

        List<ProductDTO> productDTOS = productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok(productDTOS);
    }
}
