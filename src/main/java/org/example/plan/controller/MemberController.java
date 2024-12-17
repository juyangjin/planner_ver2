package org.example.plan.controller;

import lombok.RequiredArgsConstructor;
import org.example.plan.dto.SignUpRequestDto;
import org.example.plan.dto.SignUpResponseDto;
import org.example.plan.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto =
                memberService.signUp(
                        requestDto.getUsername(),
                        requestDto.getE_mail()
                );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }
}
