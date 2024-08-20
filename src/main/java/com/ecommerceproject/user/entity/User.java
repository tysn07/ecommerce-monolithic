package com.ecommerceproject.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Builder
    public User(String email, String username, String password, UserRoleEnum role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long userId, String username, String email, UserRoleEnum role) {
        this.id = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public void changePassword(String password){
        this.password = password;

    }


}
