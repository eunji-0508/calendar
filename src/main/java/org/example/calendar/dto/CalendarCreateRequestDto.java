package org.example.calendar.dto;

import lombok.Getter;

// 일정 생성용 RequestDto
@Getter
public class CalendarCreateRequestDto {
    private String title;
    private String contents;
    private String name;
    private String passwd;
}
