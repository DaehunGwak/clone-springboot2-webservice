package com.ordi.book.springboot.service;

import com.ordi.book.springboot.domain.posts.PostsRepository;
import com.ordi.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 빈을 주입 받는 방식
 * - @Autowired
 * - setter
 * - 생성자 (제일 권장)
 */
@RequiredArgsConstructor // final 선언된 모든 필드를 인자로 가지는 생성자
@Service
public class PostService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }
}
