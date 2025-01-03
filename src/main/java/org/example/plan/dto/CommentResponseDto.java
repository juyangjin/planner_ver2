package org.example.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.plan.entity.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String comment;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;


    public static CommentResponseDto toDto (Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getUsername(),comment.getPlan().getTitle(), comment.getComment(),comment.getCreatedAt(),comment.getModifiedAt());
    }
}
