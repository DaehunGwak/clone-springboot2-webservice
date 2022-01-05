package com.ordi.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication // 자동 설정, Bean 읽기 생성 모두 자동으로 됨, 해당 위치 부터 스캔이 되기 때문에 프로젝트 최상단에 위치해야 함
public class Application {
    public static void main(String[] args) {
        // 내장형 WAS 실행 -> 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있음
        // 따로 tomcat을 설치할 필요가 없음
        // tomcat 역시 servlet 으로 이루어진 자바 애플리케이션
        SpringApplication.run(Application.class, args);
    }
}
