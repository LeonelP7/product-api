package com.example.demo.product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.exeptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @CachePut(value="productCache", key="#command.getId()")
    public ResponseEntity execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }

        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok(new ProductDTO(product));
    }
}
