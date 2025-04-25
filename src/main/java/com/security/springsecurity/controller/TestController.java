package com.security.springsecurity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.security.springsecurity.dto.MemberDTO;
import com.security.springsecurity.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/test")
  public String test() {
    return new String();
  }

  @PostMapping("/api/member/join")
  public int join(@RequestBody MemberDTO dto) {

    dto.setPassword(passwordEncoder.encode(dto.getPassword()));

    int result = memberService.join(dto);

    return result;
  }

}
