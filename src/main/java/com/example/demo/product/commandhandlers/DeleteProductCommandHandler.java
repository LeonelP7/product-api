package com.example.demo.product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<Integer, ResponseEntity> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(Integer id) {

        Optional product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        productRepository.delete((Product) product.get());
        return ResponseEntity.ok().build();
    }
}
