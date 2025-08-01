package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalendarRequestDto;
import org.example.calendar.dto.CalendarResponseDto;
import org.example.calendar.service.CalendarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    // 생성
    @PostMapping("/calendars")
    public CalendarResponseDto createCalendar(
            @RequestBody CalendarRequestDto calendarRequestDto
    ) {
        return calendarService.createCalendar(calendarRequestDto);
    }
}
