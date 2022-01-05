package com.ordi.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After // 단위 테스트가 끝날때 마다 실행
    public void cleanup() {
//        postsRepository.deleteAll(); // h2 db 다 날림
        // 엥 deleteAll 하는데 이전에 select 쿼리가 나간다?
        /* org.springframework.data.jpa.repository.support.SimpleJpaRepository 에서 힌트를 발견
         * 기본적인 구현체에서 아래와 같이 구현 됨
         * @Transactional
            public void deleteAll() {

                for (T element : findAll()) {
                    delete(element);
                }
            }
         * 따라서, select 한번, delete 건수 만큼 쿼리가 나가게됨... ㄸㄷ
         */
        postsRepository.deleteAllInBatch(); // delete from posts
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        Posts targetPosts = Posts.builder() // save -> insert/update 쿼리 (id 유 -> insert, 무 -> update)
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build();
        postsRepository.save(targetPosts);

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 1, 6, 0, 0, 0);
        Posts samplePosts = Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        postsRepository.save(samplePosts);

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>> createDate=" + posts.getCreatedDate()
                + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
