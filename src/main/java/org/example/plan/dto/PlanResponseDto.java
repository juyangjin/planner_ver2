package org.example.plan.dto;

import lombok.Getter;
import org.example.plan.entity.Plan;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PlanResponseDto(Long id, String username, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public static PlanResponseDto toDto (Plan plan){
        return new PlanResponseDto(plan.getId(), plan.getUsername(), plan.getTitle(), plan.getContents(),plan.getCreatedAt(),plan.getModifiedAt());
    }
}
