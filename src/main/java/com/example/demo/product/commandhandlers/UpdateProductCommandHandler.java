package com.example.demo.product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.UpdateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(UpdateProductCommand command) {
        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
