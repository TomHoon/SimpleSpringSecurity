package com.security.springsecurity.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class TestController {

  @GetMapping("/test")
  public String test() {
    return new String();
  }

  @PostMapping("/api/member/join")
  public String join() {
    return "hello join";
  }

}
