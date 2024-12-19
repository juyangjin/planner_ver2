package org.example.plan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

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

    /*
    이메일 검증을 위해 @Email을 사용하였다.
     */
    @Email
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
