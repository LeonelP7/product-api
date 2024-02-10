package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return ResponseEntity.ok(customerRepository.findById(id).get());
    }

    @PostMapping("/{id}")
    public ResponseEntity updateCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return ResponseEntity.ok().build();
    }

}
