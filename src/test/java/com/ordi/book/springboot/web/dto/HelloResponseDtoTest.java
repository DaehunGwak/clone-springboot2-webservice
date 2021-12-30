package com.ordi.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * assertj 장점
 * - 추가적 라이브러리 필요 X, JUnit 기본지원
 * - 자동완성 지원이 잘됨
 *
 * 백기선님의 왜 assertj를 사용하는가?
 * https://www.youtube.com/watch?v=zLx_fI24UXM
 */
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
