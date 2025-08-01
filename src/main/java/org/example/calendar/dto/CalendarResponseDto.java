package org.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;
    private final LocalDateTime createAt;     // 작성일
    private final LocalDateTime modifiedAt;   // 수정일

    public CalendarResponseDto(Long id, String title, String contents, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
