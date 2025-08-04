package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.*;
import org.example.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    // 일정 생성
    @PostMapping("/calendars")
    public ResponseEntity<CalendarCreateResponseDto> createCalendar(
            @RequestBody CalendarCreateRequestDto calendarCreateRequestDto
    ) {
        return ResponseEntity.ok(calendarService.createCalendar(calendarCreateRequestDto));
    }

    // 전체 일정 조회
    // @RequestParam(required = )로 파라미터 필수 여부 결정 가능함
    @GetMapping("/calendars")
    public ResponseEntity<List<CalendarReadResponseDto>> getCalendar(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(calendarService.getCalendar(name));
    }

    // 선택 일정 조회
    @GetMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarReadResponseDto> getCalendar(
            @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(calendarService.getCalendar(calendarId));
    }

    // 일정 수정
    @PatchMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarUpdateResponseDto> updateCalendar(
            @PathVariable Long calendarId,
            @RequestBody CalendarUpdateRequestDto calendarUpdateRequestDto
    ) {
        return ResponseEntity.ok(calendarService.updateCalendar(calendarId, calendarUpdateRequestDto));
    }

    // 일정 삭제
    // ResponseEntity<Void> 쓰면 되지만 같으니까 그냥 void 써도 됨
    @DeleteMapping("/calendars/{calendarId}")
    public void deleteCalendar(
            @PathVariable Long calendarId,
            @RequestBody CalendarDeleteRequestDto calendarDeleteRequestDto
    ) {
        calendarService.deleteCalendar(calendarId, calendarDeleteRequestDto);
    }
}
