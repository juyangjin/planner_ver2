package org.example.plan.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String username;
    private final String e_mail;
    private final String password;

    public SignUpRequestDto(String username, String eMail, String password) {
        this.username = username;
        this.e_mail = eMail;
        this.password = password;
    }
}
