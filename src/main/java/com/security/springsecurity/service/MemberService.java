package com.security.springsecurity.service;

import org.springframework.stereotype.Service;

import com.security.springsecurity.dto.MemberDTO;
import com.security.springsecurity.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public int join(MemberDTO dto) {
        return memberRepository.join(dto);
    }

}
