package org.example.calendar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Calendar extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;               // 일정 제목
    private String contents;            // 일정 내용
    private String name;                // 작성자명
    private String passwd;              // 비밀번호

    public Calendar(String title, String contents, String name, String passwd) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.passwd = passwd;
    }
}
