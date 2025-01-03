package org.example.plan.service;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.plan.config.PasswordEncoder;
import org.example.plan.dto.LoginRequestDto;
import org.example.plan.entity.Member;
import org.example.plan.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<UUID, String> sessionMap = new HashMap<>();

    public String getMemberEmail(UUID memberId) {
        return sessionMap.get(memberId);
    }

    public void login(LoginRequestDto dto, HttpSession session) throws AuthException {
        Member member = memberRepository.findMemberByEmailOrElseThrow(dto.getEmail());
        UUID sessionKey = (UUID) session.getAttribute("sessionKey");
        String storedEmail = sessionKey != null ? sessionMap.get(sessionKey) : null;

        if(storedEmail == null || !storedEmail.equals(member.getEmail())) {
            throw new AuthException("이미 로그인 되었습니다.");
        }

        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())){
            throw new AuthException ("비밀번호가 잘못되었습니다.");
        }
            UUID uuid = UUID.randomUUID();
            session.setAttribute("sessionKey", uuid);
            sessionMap.put(uuid, member.getEmail());
    }

    public void logout(HttpSession session) {
        UUID uuid = (UUID) session.getAttribute("sessionKey");
        if (uuid != null) {
            sessionMap.remove(uuid);
        }
        session.invalidate();
    }

}
