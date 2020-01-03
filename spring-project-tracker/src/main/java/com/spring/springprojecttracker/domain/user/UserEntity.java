package com.spring.springprojecttracker.domain.user;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public UserEntity(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
