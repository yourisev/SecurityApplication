package com.peam.springsecurity.controller;

import com.peam.springsecurity.repository.CustomerRepository;
import com.peam.springsecurity.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class LoginController {

    CustomerRepository repository;

    public LoginController(CustomerRepository repository) {
        this.repository = repository;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
//        Customer savedCustomer = null;
//        ResponseEntity response = null;
//        try {
//            String hashPwd = passwordEncoder.encode(customer.getPwd());
//            customer.setPwd(hashPwd);
//            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
//            savedCustomer = repository.save(customer);
//            if (savedCustomer.getId() > 0) {
//                response = ResponseEntity
//                        .status(HttpStatus.CREATED)
//                        .body("Given user details are successfully registered");
//            }
//        } catch (Exception ex) {
//            response = ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An exception occured due to " + ex.getMessage());
//        }
//        return response;
//    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = repository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }
}
