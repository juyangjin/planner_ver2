package org.example.plan.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanUpdateRequestDto {
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PlanUpdateRequestDto(String username, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
