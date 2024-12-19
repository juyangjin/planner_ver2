package org.example.plan.dto;

import lombok.Getter;
import org.example.plan.entity.Comment;
import org.example.plan.entity.Member;
import org.example.plan.entity.Plan;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String comment;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Long id, String username, String title, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.comment = comment;
        this.createAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponseDto toDto (Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getUsername(),comment.getPlan().getTitle(), comment.getComment(),comment.getCreatedAt(),comment.getModifiedAt());
    }
}
