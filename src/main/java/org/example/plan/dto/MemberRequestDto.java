package org.example.plan.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    private final String username;
    private final String e_mail;

    public MemberRequestDto(String username, String eMail) {
        this.username = username;
        this.e_mail = eMail;
    }
}
