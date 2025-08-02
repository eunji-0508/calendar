package org.example.calendar.dto;

import lombok.Getter;
import java.time.LocalDateTime;

// 일정 조회용 ResponseDto
@Getter
public class CalendarReadResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 일정 조회에 사용되는 생성자
    public CalendarReadResponseDto(Long id, String title, String contents, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
