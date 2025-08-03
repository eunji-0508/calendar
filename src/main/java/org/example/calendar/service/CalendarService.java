package org.example.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.*;
import org.example.calendar.entity.Calendar;
import org.example.calendar.repository.CalendarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    // 일정 생성 (Create)
    // 일정 제목, 일정 내용, 작성자명, 비밀번호, 작성/수정일을 저장함
    @Transactional
    public CalendarCreateResponseDto createCalendar(CalendarCreateRequestDto calendarCreateRequestDto) {
        Calendar calendar = new Calendar(
                calendarCreateRequestDto.getTitle(),
                calendarCreateRequestDto.getContents(),
                calendarCreateRequestDto.getName(),
                calendarCreateRequestDto.getPasswd()
        );

        Calendar savedCalendar = calendarRepository.save(calendar);

        // API 응답에 비밀번호는 제외함
        return new CalendarCreateResponseDto(
                savedCalendar.getId(),
                savedCalendar.getTitle(),
                savedCalendar.getContents(),
                savedCalendar.getName(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }

    // 전체 일정 조회 (Read)
    // 트러블 슈팅 : findAllByName 메서드는 존재하지 않음
    @Transactional(readOnly = true)
    public List<CalendarReadResponseDto> getCalendar(String name) {
        List<Calendar> calendars;

        // 트러블 슈팅 : 무조건 이름은 들어온다고 생각함
        if (name == null || name.isEmpty()) {
            calendars = calendarRepository.findAll();
        } else {
            calendars = calendarRepository.findAllByName(name);
        }

        // 트러블 슈팅: 수정일 기준 내림차순 정렬 (블로그 참고)
        List<CalendarReadResponseDto> dtoList = calendars.stream().map(calendar -> new CalendarReadResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContents(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        )).sorted(Comparator.comparing(CalendarReadResponseDto::getModifiedAt).reversed()).collect(Collectors.toList());

        return dtoList;
    }

    // 단일 일정 조회 (read)
    @Transactional(readOnly = true)
    public CalendarReadResponseDto getCalendar(Long id) {
        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );
        return new CalendarReadResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContents(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    // 일정 수정 (Update)
    @Transactional
    public CalendarUpdateResponseDto updateCalendar(Long id, CalendarUpdateRequestDto calendarUpdateRequestDto) {
        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        // "exit".equals(exit) 형태로는 둘 다 값을 받아와야 하니 못씀
        // Condition 'calendarUpdateRequestDto.getPasswd() == null' is always 'false' 이라고 노란줄로 알려줌
        // 입력 안하고 null 보낼 수도 있지 않나요?
        if (calendar.getPasswd() == null || calendarUpdateRequestDto.getPasswd() == null
                || calendarUpdateRequestDto.getPasswd().isEmpty() || calendar.getPasswd().isEmpty()
                || !calendarUpdateRequestDto.getPasswd().equals(calendar.getPasswd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        calendar.updateTwoField(calendarUpdateRequestDto.getTitle(), calendarUpdateRequestDto.getName());

        return new CalendarUpdateResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContents(),
                calendar.getName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    // 일정 삭제 (Delete)
    @Transactional
    public void deleteCalendar(Long id, CalendarDeleteRequestDto calendarDeleteRequestDto) {
        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        if (calendar.getPasswd() == null || calendarDeleteRequestDto.getPasswd() == null
                || calendarDeleteRequestDto.getPasswd().isEmpty() || calendar.getPasswd().isEmpty()
                || !calendarDeleteRequestDto.getPasswd().equals(calendar.getPasswd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        calendarRepository.deleteById(id);
    }
}
