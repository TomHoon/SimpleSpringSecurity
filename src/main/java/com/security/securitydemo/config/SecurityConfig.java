package com.security.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.securitydemo.filter.FilterBefore;
import com.security.securitydemo.utils.RestAPILoginFailHandler;
import com.security.securitydemo.utils.RestAPILoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(config -> config.disable());
        
        http.formLogin(config -> {
            config.loginPage("/api/member/login");
            config.successHandler(new RestAPILoginSuccessHandler());
            config.failureHandler(new RestAPILoginFailHandler());
        });

        http.addFilterBefore(new FilterBefore(),
        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
