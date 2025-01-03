package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    private final String username;
    private final String email;

}
