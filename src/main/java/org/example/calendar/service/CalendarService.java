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
        return calendars.stream().map(calendar -> new CalendarReadResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContents(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        )).sorted(Comparator.comparing(CalendarReadResponseDto::getModifiedAt).reversed()).collect(Collectors.toList());

    }

    // 단일 일정 조회 (read)
    @Transactional(readOnly = true)
    public CalendarReadResponseDto getCalendar(Long id) {
        // 같은 클래스 내부에서는 메서드를 = 메서드명() 형태로 호출할 수 있음
        // 익숙한 형태가 아니라 다소 어색해보여서 자주 사용하면서 연습하면 좋을 것 같음
        Calendar calendar = findCalendarById(id);

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
        Calendar calendar = findCalendarById(id);

        // "exit".equals(exit) 형태로는 둘 다 값을 받아와야 하니 못씀
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
        Calendar calendar = findCalendarById(id);

        if (calendar.getPasswd() == null || calendarDeleteRequestDto.getPasswd() == null
                || calendarDeleteRequestDto.getPasswd().isEmpty() || calendar.getPasswd().isEmpty()
                || !calendarDeleteRequestDto.getPasswd().equals(calendar.getPasswd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        calendarRepository.deleteById(id);
    }

    // 비밀번호 검증 메서드 (튜터님 추천으로 만들었음)
    // 반복되는 부분을 메서드로 추출하여 더 깔끔하게 작성하는 것이 가능함
    private Calendar findCalendarById(Long id) {
        return calendarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );
    }
}
