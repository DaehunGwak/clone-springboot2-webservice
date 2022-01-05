package com.ordi.book.springboot.service;

import com.ordi.book.springboot.domain.posts.Posts;
import com.ordi.book.springboot.domain.posts.PostsRepository;
import com.ordi.book.springboot.web.dto.PostsResponseDto;
import com.ordi.book.springboot.web.dto.PostsSaveRequestDto;
import com.ordi.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 빈을 주입 받는 방식
 * - @Autowired
 * - setter
 * - 생성자 (제일 권장)
 */
@RequiredArgsConstructor // final 선언된 모든 필드를 인자로 가지는 생성자
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }

    /**
     * 영속성 컨텍스트가 변경을 감지하고 알아서 업데이트를 날려줌
     * - 트랜잭션이 끝나는 시점에 변경을 감지하여 업데이트 쿼리를 반영 (더티체킹)
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}
