package org.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String name;
    private String passwd;
    private LocalDateTime createdAt;     // 작성일
    private LocalDateTime modifiedAt;   // 수정일
}
