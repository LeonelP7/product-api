package com.example.demo.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){

        //Usually, data validation is performed in the database

        if (request.getUsername().equals("admin") && request.getPassword().equals("1")){
            String token = JwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
