package com.ordi.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// @Setter 가 없음 -> Java bean 규약은 setter/getter 필요
// Entity는 절대 Setter 메소드를 만들지 않음
// 대신 의도를 나타내는 메서드로 대신
@Getter
@NoArgsConstructor
@Entity // 필수인걸 클래스 가까이
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이걸 붙여야만 auto_increment
    private Long id; // Long -> bigint(mysql)

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
