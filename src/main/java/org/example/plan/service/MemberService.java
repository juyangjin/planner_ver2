package org.example.plan.service;

import lombok.RequiredArgsConstructor;
import org.example.plan.dto.SignUpResponseDto;
import org.example.plan.entity.Member;
import org.example.plan.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String e_mail) {

        Member member = new Member(username, e_mail);
        Member saveMember = memberRepository.save(member);

        return new SignUpResponseDto(saveMember.getId(),saveMember.getUsername(),saveMember.getE_mail());
    }
}
