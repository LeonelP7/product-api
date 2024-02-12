package com.example.demo.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/special")
    public String special(){
        return "SPECIAL";
    }

    @GetMapping("/basic")
    public String basic(){
        return "BASIC";
    }
}
