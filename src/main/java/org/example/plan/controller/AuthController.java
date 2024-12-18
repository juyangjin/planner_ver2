package org.example.plan.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.plan.dto.LoginRequestDto;
import org.example.plan.dto.MemberResponseDto;
import org.example.plan.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //로그인을 위한 PostMapping
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        String sessionKey = authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword(), session);

        if(sessionKey == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증에 실패했습니다.");
        }
        return ResponseEntity.ok("로그인을 성공했습니다.");
    }


    //로그아웃을 위한 PostMapping
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        authService.logout(session);
        return ResponseEntity.ok("로그아웃에 성공했습니다.");
    }
}
