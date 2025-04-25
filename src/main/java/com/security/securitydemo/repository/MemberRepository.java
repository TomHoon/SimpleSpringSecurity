package com.security.securitydemo.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.security.securitydemo.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    
    private final SqlSessionTemplate sqlSessionTemplate;

    public int join(MemberDTO dto) {
        return sqlSessionTemplate.insert("MemberMapper.join", dto);
    }
}
