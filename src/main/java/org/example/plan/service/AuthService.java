package org.example.plan.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.plan.entity.Member;
import org.example.plan.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public String login(String email, String password, HttpSession session) {
        Member member = memberRepository.findMemberByEmailOrElseThrow(email);

        if(!member.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," 비밀번호 오답");
        }

        session.setAttribute("sessionKey",member.getEmail());
        return member.getEmail();
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

}
