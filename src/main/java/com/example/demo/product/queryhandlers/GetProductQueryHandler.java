package com.example.demo.product.queryhandlers;

import com.example.demo.Query;
import com.example.demo.exeptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ProductNotFoundException();
        }
        ProductDTO productDTO = new ProductDTO((Product) product.get());

        return ResponseEntity.ok(productDTO);
    }
}
