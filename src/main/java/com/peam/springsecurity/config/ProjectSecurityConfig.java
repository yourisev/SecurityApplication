package com.peam.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                    .requestMatchers("/contact","/notices").permitAll()
                    .anyRequest().permitAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        /*
        Approach using NoOpPasswordEncoderBean instead which
        is not used in production since it does not apply any
        encryption on the password.
         */

        UserDetails user1 = User.withUsername("dev1")
                .password("126974")
                .authorities("admin")
                .build();

        UserDetails user2 = User.withUsername("user1")
                .password("004653")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
