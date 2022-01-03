package com.ordi.book.springboot.web.dto;

import com.ordi.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /**
     * Entity 클래스는 DB와 맞닿은 핵심 클래스라
     * Request/Response -> Entity 로 지정하는 것이 좋음.
     *
     * Entity DB와 맞닿기 때문에 이를 수정하는 것은 매우 큰변경,
     * 그에 비해 Request, Response는 자주 바뀔 수도 있고 Entity 변경에 비해서는 작은 변경
     */
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
