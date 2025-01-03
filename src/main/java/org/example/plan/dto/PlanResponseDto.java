package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.plan.entity.Plan;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static PlanResponseDto toDto (Plan plan){
        return new PlanResponseDto(plan.getId(), plan.getTitle(), plan.getContents(),plan.getCreatedAt(),plan.getModifiedAt());
    }
}
