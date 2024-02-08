package com.example.noBSSpringBoot.product.commandhandlers;

import com.example.noBSSpringBoot.Command;
import com.example.noBSSpringBoot.product.ProductRepository;
import com.example.noBSSpringBoot.product.model.Product;
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
