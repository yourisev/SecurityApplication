package com.peam.springsecurity.controller.secure;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

    @GetMapping("/")
    public String initialAuth(OAuth2AuthenticationToken token){
        System.out.println(token.getPrincipal());
        return "secure.html";
    }
}
