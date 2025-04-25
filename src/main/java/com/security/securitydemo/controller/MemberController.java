package com.security.securitydemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.securitydemo.dto.MemberDTO;
import com.security.securitydemo.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    
    private final MemberService memberService;


    @PostMapping("/member/join")
    public String join(@RequestParam MemberDTO dto) {
        
        int result = memberService.join(dto);

        return result > 0 ? "성공" : "실패";
    }
    
}
