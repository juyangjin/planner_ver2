package org.example.plan.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    private final String username;
    private final String email;

    public MemberRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
