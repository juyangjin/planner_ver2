package org.example.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String e_mail;

    public Member(String username, String e_mail) {

    }

    public Member(String username, String e_mail, String password){
        this.username = username;
        this.e_mail = e_mail;
    }

}
