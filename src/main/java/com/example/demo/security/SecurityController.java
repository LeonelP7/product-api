package com.example.demo.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/open")
    public String open(){
        return "No login required";
    }

    @GetMapping("/closed")
    public String closed(){
        return "Login is required";
    }
}
