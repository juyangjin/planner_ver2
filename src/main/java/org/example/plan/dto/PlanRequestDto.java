package org.example.plan.dto;

import lombok.Getter;

@Getter
public class PlanRequestDto {
    private final String username;
    private final String title;
    private final String contents;
    private final String password;

    public PlanRequestDto(String username, String title, String contents, String password) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
}
