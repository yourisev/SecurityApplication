package com.peam.springsecurity.controller;

import com.peam.springsecurity.CustomerRepository;
import com.peam.springsecurity.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    CustomerRepository repository;

    PasswordEncoder passwordEncoder;

    public LoginController(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedUser = null;
        ResponseEntity response = null;
        try {
            String hashPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPassword);
            savedUser = repository.save(customer);
            if (savedUser.getId() > 0){
                response = ResponseEntity.
                        status(HttpStatus.CREATED).
                        body("User saved successfully");
            }
        }catch (Exception e){
            response = ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred due to "+ e.getMessage());
        }
        return response;
    }
}
