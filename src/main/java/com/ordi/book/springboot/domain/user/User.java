package com.ordi.book.springboot.domain.user;

import com.ordi.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(indexes = @Index(name = "index_user_email", columnList = "email"))
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /**
     * 기본 Enumerated 의 EnumType = EnumType.ORDINAL
     * - int 로 db에 적히게 됨 (Enum 정의 순서에 따른)
     * - 숫자로 지정되면 DB 에 숫자로 기록되어 파악이 어려워 짐
     * - 고로 EnumType.STRING 으로 설정
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
