package com.ordi.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository의 이점 및 특징
 * - crud 메서드 자동 생성
 * - @Repository 붙일 필요 없음
 * - Posts 와 밀접하니 domain 의 같은 위치에 존재
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * Spring Data JPA 에서 제공하지 않는 메서드는 아래와 같이 jpql 로 짤 수 있음
     * 아래와 같이 심플 쿼리면 저렇게 짜도 되지만, 복잡할 경우 조회용 프레임워크를 추가적으로 사용
     *
     * 선택지: querydsl, jooq, mybatis(맵퍼) 등이 있음
     * querydsl 을 추천하는 이유
     * - 많은 레퍼런스
     * - 국내 많은 회사 사용
     * - 타입 안정성 보장 (오탈자 걱정 X)
     * @return
     */
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // jpql
    List<Posts> findAllDesc();
}
