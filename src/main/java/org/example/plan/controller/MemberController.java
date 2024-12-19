package org.example.plan.controller;

import lombok.RequiredArgsConstructor;
import org.example.plan.config.PasswordEncoder;
import org.example.plan.dto.*;
import org.example.plan.entity.Plan;
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

    /*
    로그인 필터에서 로그인이 되어있지 않으면 중단이 되게끔 구현되어 있기 때문에
    그것을 방지하기 위해 PostMapping으로 로그인 URL을 설정하였다.
    이메일이 형태에 맞지 않으면 가입했을 때 효용성이 없으므로 이메일 형식에 대한 예외처리를 구현하였다.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp( //예외처리를 위해 여러가지 타입으로 return 가능한 ? 사용
           @RequestBody SignUpRequestDto requestDto,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasFieldErrors("email")) {
            FieldError emailError = bindingResult.getFieldError("email");
            if (emailError != null) {
                String errorMessage = emailError.getDefaultMessage();
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
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

    /*
    전체 사용자를 조회하기 위해 값을 List 형식으로 불러오게 하였다.
     */
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        List<MemberResponseDto> memberResponseDtoList = memberService.findAll();

        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.OK);
    }

    /*
    하나의 사용자만 조회하기 위해 id를 이용하여 조회할 수 있게 하였다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    /*
    값을 수정하기 위해 id로 수정할 값을 가져와서 그 위치에 새로운 값을 넣을 수 있게 하였다.
     */
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

    /*
    사용자를 삭제하기 위해 DeleteMapping을 사용하였다.
    애초에 로그인 필터가 있기 때문에 사용자 수정과 삭제에서는 일단 비밀번호 검증을 하지 않았다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
