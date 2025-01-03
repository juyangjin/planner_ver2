package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.plan.entity.Member;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    //회원가입과 조회를 구분해서 사용하기 위해서 클래스 분리
    private final Long id;
    private final String username;
    private final String email;


    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }
}
