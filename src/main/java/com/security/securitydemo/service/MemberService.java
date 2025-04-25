package com.security.securitydemo.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    public Map<String, String> getMemberFromToken(String accessToken) {
        String kakaoURL = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded");

        HttpEntity entity = new HttpEntity<>(headers);
        UriComponents uriBuilder = UriComponentsBuilder.fromUriString(kakaoURL).build();

        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(
                uriBuilder.toString(),
                HttpMethod.GET,
                entity,
                LinkedHashMap.class);

        LinkedHashMap<String, LinkedHashMap> bodyMap = response.getBody();
        LinkedHashMap<String, String> kakao_account = bodyMap.get("kakao_account");
        LinkedHashMap<String, String> kakao_properties = bodyMap.get("properties");

        Map<String, String> result = new HashMap<>();
        result.put("email", kakao_account.get("email"));
        result.put("name", kakao_account.get("name"));

        String profile_image = kakao_properties.get("profile_image");
        result.put("profile_image", profile_image);

        return result;
    }
}
