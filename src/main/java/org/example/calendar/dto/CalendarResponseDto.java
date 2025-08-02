package org.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CalendarResponseDto(Long id, String title, String contents, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.createdAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
