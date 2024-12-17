package org.example.plan.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String username;
    private final String e_mail;
    private final String password;

    public SignUpResponseDto(Long id, String username, String eMail, String password) {
        this.id = id;
        this.username = username;
        this.e_mail = eMail;
        this.password = password;
    }


}
