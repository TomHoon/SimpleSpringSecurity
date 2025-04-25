package com.security.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.springsecurity.filter.FilterBefore;
import com.security.springsecurity.handler.RestAPILoginFailHandler;
import com.security.springsecurity.handler.RestAPILoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CustomSecurity {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(config -> config.disable());

    // 로그인 경로설정
    // 성공,실패 핸들러 커스텀
    http.formLogin(config -> {
      config.loginPage("/api/member/login");
      config.successHandler(new RestAPILoginSuccessHandler());
      config.failureHandler(new RestAPILoginFailHandler());
    });

    http.addFilterBefore(new FilterBefore(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Or use another encoder like Pbkdf2PasswordEncoder
  }

}
