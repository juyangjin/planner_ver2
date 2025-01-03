package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private final Long id;
    private final String username;
    private final String comment;

}
