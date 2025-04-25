package com.security.springsecurity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.springsecurity.dto.MemberDTO;
import com.security.springsecurity.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

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

  @PostMapping("/api/kakao/token")
  public Map<String, String> kakaoToken(@RequestBody String tokenString)
      throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> mapped = mapper.readValue(tokenString, Map.class);

    String token = (String) mapped.get("accessToken");

    System.out.println(token);

    Map<String, String> result = memberService.getMemberFromToken(token);

    return result;
  }

}
