package org.example.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Calendar extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 블로그 참고
    // ERD 작성 중 일정 제목, 작성자명은 NULL 아니였으면 좋겠다는 생각이 들었음
    // JPA 기초 실습 (1) @Column(unique = true) -> 이 컬럼은 유니크 해야 함을 보고 고민함
    // @Column(nullable = false)을 쓰면 NULL이 사용될 수 없다는 것을 알게됨
    // 트러블슈팅: DB에 반영이 안됨 (application.yml에서 update를 create로 바꾼 후 한 번 다시 실행해야 반영됨)
    // 제약조건은 update로 반영이 안됨
    @Column(nullable = false)
    private String title;               // 일정 제목

    private String contents;            // 일정 내용

    @Column(nullable = false)
    private String name;                // 작성자명

    private String passwd;              // 비밀번호

    // 생성자
    public Calendar(String title, String contents, String name, String passwd) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.passwd = passwd;
    }

    public void updateTwoField(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
