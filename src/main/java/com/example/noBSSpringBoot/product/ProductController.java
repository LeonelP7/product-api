package com.example.noBSSpringBoot.product;

import com.example.noBSSpringBoot.product.commandhandlers.CreateProductCommandHandler;
import com.example.noBSSpringBoot.product.commandhandlers.DeleteProductCommandHandler;
import com.example.noBSSpringBoot.product.commandhandlers.UpdateProductCommandHandler;
import com.example.noBSSpringBoot.product.model.Product;
import com.example.noBSSpringBoot.product.model.UpdateProductCommand;
import com.example.noBSSpringBoot.product.queryhandlers.GetAllProductsQueryHandler;
import com.example.noBSSpringBoot.product.queryhandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GetAllProductsQueryHandler getAllProductsQueryHandler;

    @Autowired
    private GetProductQueryHandler getProductQueryHandler;

    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;

    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;

    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        return getProductQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product){
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product){
        UpdateProductCommand update = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        return deleteProductCommandHandler.execute(id);
    }

}
