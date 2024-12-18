package org.example.plan.controller;

import lombok.RequiredArgsConstructor;
import org.example.plan.config.PasswordEncoder;
import org.example.plan.dto.*;
import org.example.plan.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //유저 회원가입을 위해 PostMapping에 주소 설정
    @PostMapping("/signup")
    public ResponseEntity<?> signUp( //예외처리를 위해 여러가지 타입으로 return 가능한 ? 사용
           @RequestBody SignUpRequestDto requestDto,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasFieldErrors("email")) { // 이메일 필드에 에러가 있는지 확인
            FieldError emailError = bindingResult.getFieldError("email"); // 이메일 에러 가져오기
            if (emailError != null) {
                String errorMessage = emailError.getDefaultMessage(); // 에러 메시지 추출
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST); // 에러 응답
            }
        }


        SignUpResponseDto signUpResponseDto =
                memberService.signUp(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        List<MemberResponseDto> memberResponseDtoList = memberService.findAll();

        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long id,
            @RequestBody MemberRequestDto requestDto

    ) {
        MemberResponseDto responseDto =
                memberService.updateMember(
                        id,
                        requestDto.getUsername(),
                        requestDto.getEmail()
                );
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
