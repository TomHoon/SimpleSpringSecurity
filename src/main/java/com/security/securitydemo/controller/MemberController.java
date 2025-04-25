package com.security.securitydemo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.securitydemo.dto.MemberDTO;
import com.security.securitydemo.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/join")
    public String join(@RequestBody MemberDTO dto) {

        int result = memberService.join(dto);

        return result > 0 ? "성공" : "실패";
    }

    @PostMapping("/kakao/token")
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
