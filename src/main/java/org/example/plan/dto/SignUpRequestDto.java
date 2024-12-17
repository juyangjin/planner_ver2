package org.example.plan.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String username;
    private final String e_mail;

    public SignUpRequestDto(String username, String eMail) {
        this.username = username;
        e_mail = eMail;
    }
}
