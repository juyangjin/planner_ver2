package org.example.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String username;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public Member() {

    }

}
