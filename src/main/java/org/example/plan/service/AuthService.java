package org.example.plan.service;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.plan.config.PasswordEncoder;
import org.example.plan.entity.Member;
import org.example.plan.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password, HttpSession session) throws AuthException {
        Member member = memberRepository.findMemberByEmailOrElseThrow(email);


        if(!passwordEncoder.matches(password,member.getPassword())){
            throw new AuthException ("비밀번호 오답");
        }

        session.setAttribute("sessionKey",member.getEmail());
        return member.getEmail();
    }

}
