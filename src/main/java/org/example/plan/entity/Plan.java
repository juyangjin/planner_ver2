package org.example.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

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

    /*
    일정에서 유저를 N:1로 사용할 수 있게 조인하였다.
    유저는 하나이지만 일정을 여러 개 쓸 수 있다.
     */
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
