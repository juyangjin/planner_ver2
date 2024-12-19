package org.example.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Setter
    @Column
    private String comment;

    /*
    댓글 테이블에서 일정과 유저를 N:1로 사용할 수 있도록 하였다.
    유저와 일정은 각각 하나이지만 여러 댓글을 달 수 있다.
     */
    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;


    public Comment (String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    public Comment() {

    }
}
