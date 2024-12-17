package org.example.plan.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String username;
    private final String e_mail;

    public SignUpResponseDto(Long id, String username, String eMail) {
        this.id = id;
        this.username = username;
        e_mail = eMail;
    }
}
