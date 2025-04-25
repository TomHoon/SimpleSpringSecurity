package com.security.securitydemo.service;

import org.springframework.stereotype.Service;

import com.security.securitydemo.dto.MemberDTO;
import com.security.securitydemo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;


    public int join(MemberDTO dto) {
        return memberRepository.join(dto);
    }
}
