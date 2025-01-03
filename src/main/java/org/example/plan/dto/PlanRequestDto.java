package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlanRequestDto {
    private final String username;
    private final String title;
    private final String contents;
    private final String password;
}
