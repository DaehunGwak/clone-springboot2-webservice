package com.ordi.book.springboot.config.auth;

import com.ordi.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 사용을 위한
                .and()
                    .authorizeRequests() // URL 별 권한 관리 옵션 시작점
                        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated() // 나머지 url은 인증된 사용자들 에게만 허용 (로그인 된 사람만)
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 `/` 로 redirect
                .and()
                    .oauth2Login() // Oauth2 로그인 기능 설정 시작점
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보 가졀올 때의 설정 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 이후 후속 조치를 진행할 Service 등록
    }
}
