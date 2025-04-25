package com.security.springsecurity.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.security.springsecurity.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public int join(MemberDTO dto) {
        int result = sqlSessionTemplate.insert("MemberMapper.join", dto);
        return result;
    }
}
