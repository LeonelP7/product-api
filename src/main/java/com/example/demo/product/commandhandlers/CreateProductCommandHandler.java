package com.example.demo.product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.exeptions.ProductNotValidException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(Product product) {

        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product) {
        if (StringUtils.isBlank(product.getName())){
            throw new ProductNotValidException("Product name cannot be blank");
        }

        if (StringUtils.isBlank(product.getDescription())){
            throw new ProductNotValidException("Product description cannot be blank");
        }

        if (product.getPrice() <= 0){
            throw new ProductNotValidException("Product price cannot be negative");
        }

        if (product.getQuantity() <= 0){
            throw new ProductNotValidException("Product quantity cannot be negative");
        }
    }
}
