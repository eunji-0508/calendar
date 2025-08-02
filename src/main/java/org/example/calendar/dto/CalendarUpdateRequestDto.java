package org.example.calendar.dto;

import lombok.Getter;

// 일정 수정용 RequestDto
@Getter
public class CalendarUpdateRequestDto {
    private String title;
    private String name;
    private String passwd;
}
