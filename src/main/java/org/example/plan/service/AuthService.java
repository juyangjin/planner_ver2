package org.example.plan.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.plan.config.PasswordEncoder;
import org.example.plan.entity.Member;
import org.example.plan.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.regex.Pattern.matches;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password, HttpSession session) {
        Member member = memberRepository.findMemberByEmailOrElseThrow(email);


        if(!passwordEncoder.matches(password,member.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," 비밀번호 오답");
        }

        session.setAttribute("sessionKey",member.getEmail());
        return member.getEmail();
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

}
