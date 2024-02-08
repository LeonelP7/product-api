package com.example.demo.product.queryhandlers;

import com.example.demo.Query;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        ProductDTO productDTO = new ProductDTO((Product) product.get());

        return ResponseEntity.ok(productDTO);
    }
}