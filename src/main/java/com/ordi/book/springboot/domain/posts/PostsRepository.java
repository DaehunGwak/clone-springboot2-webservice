package com.ordi.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository의 이점 및 특징
 * - crud 메서드 자동 생성
 * - @Repository 붙일 필요 없음
 * - Posts 와 밀접하니 domain 의 같은 위치에 존재
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
