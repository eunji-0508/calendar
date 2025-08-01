package org.example.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalendarRequestDto;
import org.example.calendar.dto.CalendarResponseDto;
import org.example.calendar.entity.Calendar;
import org.example.calendar.repository.CalendarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    // 생성 (Create)
    @Transactional
    public CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto){
        Calendar calendar = new Calendar(
                calendarRequestDto.getTitle(),
                calendarRequestDto.getContents(),
                calendarRequestDto.getName(),
                calendarRequestDto.getPasswd()
        );

        Calendar savedCalendar = calendarRepository.save(calendar);

        return new CalendarResponseDto(
                savedCalendar.getId(),
                savedCalendar.getTitle(),
                savedCalendar.getContents(),
                savedCalendar.getName(),
                savedCalendar.getCreateAt(),
                savedCalendar.getModifiedAt()
        );
    }
}
