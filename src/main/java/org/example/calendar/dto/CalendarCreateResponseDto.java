package org.example.calendar.dto;

import lombok.Getter;
import java.time.LocalDateTime;

// 일정 생성용 ResponseDto
@Getter
public class CalendarCreateResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 일정 생성에서 사용되는 생성자
    public CalendarCreateResponseDto(Long id, String title, String contents, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.createdAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
