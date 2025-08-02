package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.*;
import org.example.calendar.service.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    // 생성
    @PostMapping("/calendars")
    public CalendarCreateResponseDto createCalendar(
            @RequestBody CalendarRequestDto calendarRequestDto
    ) {
        return calendarService.createCalendar(calendarRequestDto);
    }

    // 전체 일정 조회
    // @RequestParam(required = )로 파라미터 필수 여부 결정 가능함
    @GetMapping("/calendars")
    public List<CalendarReadResponseDto> getCalendar(@RequestParam(required = false) String name) {
        return calendarService.getCalendar(name);
    }

    // 선택 일정 조회
    @GetMapping("/calendars/{calendarId}")
    public CalendarReadResponseDto getCalendar(
            @PathVariable Long calendarId
    ) {
        return calendarService.getCalendar(calendarId);
    }

    // 일정 수정
    @PatchMapping("/calendars/{calendarId}")
    public CalendarUpdateResponseDto updateCalendar(
            @PathVariable Long calendarId,
            @RequestBody CalendarUpdateRequestDto calendarUpdateRequestDto
    ) {
        return calendarService.updateCalendar(calendarId, calendarUpdateRequestDto);
    }
}
