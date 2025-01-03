package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final String password;

}
