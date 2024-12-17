package org.example.plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.plan.dto.MemberResponseDto;
import org.example.plan.dto.SignUpResponseDto;
import org.example.plan.entity.Member;
import org.example.plan.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String e_mail) {

        Member member = new Member(username, e_mail);
        Member saveMember = memberRepository.save(member);

        return new SignUpResponseDto(saveMember.getId(),saveMember.getUsername(),saveMember.getE_mail());
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    public MemberResponseDto findById(Long id){
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) { //예외처리를 위한 조건문
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Member findMember = optionalMember.get();
        return new MemberResponseDto(id, findMember.getUsername(), findMember.getE_mail());
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, String username, String e_mail){
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        findMember.setUsername(username);
        findMember.setE_mail(e_mail);

        return new MemberResponseDto(id, username, e_mail);
    }

    public void delete(Long id){
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(findMember);
    }

}
