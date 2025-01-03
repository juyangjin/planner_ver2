package org.example.plan.controller;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.plan.dto.LoginRequestDto;
import org.example.plan.dto.MemberResponseDto;
import org.example.plan.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /*
    로그인을 위하여 인증 클래스에서 login 기능 구현
    회원가입을 할 때 암호화된 비밀번호를 세션키에 적용하여 해당 값이 비어있을 때는 인증 실패 메시지를 뜨게 하였다.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto, HttpSession session) throws AuthException {
        authService.login(dto, session);
        return new ResponseEntity<>("로그인이 성공했습니다.", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        authService.logout(session);
        return new ResponseEntity<>("로그아웃에 성공했습니다.", HttpStatus.OK);
    }
}
