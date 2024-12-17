package org.example.plan.dto;

import lombok.Getter;

@Getter
public class PlanRequestDto {
    private final String username;
    private final String title;
    private final String contents;

    public PlanRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
