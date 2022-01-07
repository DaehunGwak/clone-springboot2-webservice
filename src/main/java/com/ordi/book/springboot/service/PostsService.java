package com.ordi.book.springboot.service;

import com.ordi.book.springboot.domain.posts.Posts;
import com.ordi.book.springboot.domain.posts.PostsRepository;
import com.ordi.book.springboot.web.dto.PostsListResponseDto;
import com.ordi.book.springboot.web.dto.PostsResponseDto;
import com.ordi.book.springboot.web.dto.PostsSaveRequestDto;
import com.ordi.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 왜 속도가 개선되지?
     * 보통 JPA를 사용하면 영속성 컨텍스트가 더티 체킹, 즉 변경 감지를 위해 엔티티 조회 시점의 스냅샷을 떠야한다
     * 하지만 애초에 엔티티의 변경이 필요 없이 조회용으로만 조회하는 것이라면 엔티티의 스냅샷을 뜰 필요가 없다.
     * 그래서 readOnly = true 를 설정하여 해당 엔티티 들은 조회 목적이라는 것을 밝힐 수 있고,
     * 엔티티 스냅샷 및 더티 체킹, 플러시 등이 필요 없어지므로 성능적으로 개선된다.
     * 그리고 DB 측면에서도 트랜잭션에 읽기만 할것이라는 것을 밝히면서, 성능 최적화가 가능하다고 한다 <- 이 부분은 더 공부가 필요
     * https://junhyunny.github.io/spring-boot/jpa/junit/transactional-readonly/
     */
    @Transactional(readOnly = true) // 트랜잭션만 남겨두고 조회 기능만 남겨두어 조회 속도가 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
