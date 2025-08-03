package org.example.calendar.dto;

import lombok.Getter;

// 일정 삭제용 RequestDto
@Getter
public class CalendarDeleteRequestDto {
    private String passwd;
}
