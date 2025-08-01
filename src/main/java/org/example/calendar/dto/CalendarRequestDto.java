package org.example.calendar.dto;

import lombok.Getter;

@Getter
public class CalendarRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String name;
    private String passwd;

}
