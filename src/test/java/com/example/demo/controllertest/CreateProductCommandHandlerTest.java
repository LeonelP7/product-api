package com.example.demo.controllertest;

import com.example.demo.AmazonApiApplication;
import com.example.demo.exeptions.ProductNotValidException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.commandhandlers.CreateProductCommandHandler;
import com.example.demo.product.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AmazonApiApplication.class)
public class CreateProductCommandHandlerTest {

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProductCommandHandler_validProduct_ReturnsSuccess(){
        Product product = new Product();
        product.setName("test");
        product.setDescription("test description");
        product.setQuantity(1);
        product.setPrice(1.0);
        ResponseEntity response = createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException(){
        Product product = new Product();
        product.setName("test");
        product.setDescription("test description");
        product.setQuantity(1);
        product.setPrice(-1.0);

        ProductNotValidException exception = assertThrows(
                ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
        assertEquals("Product price cannot be negative", exception.getSimpleResponse().getMessage());
    }
}
