package com.ordi.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * JPA Auditing 을 사용한 생성/수정 시간 자동 반영
 * 생성 주체는? WAS, DB 기능을 사용하지 않고 insert 쿼리에 파라미터가 바인딩 되었음
 */
@Getter
@MappedSuperclass // BaseTimeEntity를 상속할 경우 아래 필드들도 칼럼으로 인식하도록 만듦
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate; // insert 쿼리가 날라가는 시점에 생성

    @LastModifiedDate
    private LocalDateTime modifiedDate; // insert/update 쿼리가 날라가는 시점에 생성/갱신
}
