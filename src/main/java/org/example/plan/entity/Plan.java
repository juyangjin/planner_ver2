package org.example.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "plan")
public class Plan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(columnDefinition = "longtext")
    private String contents;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public Plan(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


    public Plan() {

    }
}
