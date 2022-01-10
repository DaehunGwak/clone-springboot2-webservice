package com.ordi.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    /**
     * 스프링 시큐리티 앞 권한 코드에 항상 `ROLE_*` 이 붙어야 함
     */
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
