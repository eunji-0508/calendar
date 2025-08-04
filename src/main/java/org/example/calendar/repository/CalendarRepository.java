package org.example.calendar.repository;

import org.example.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    // 작성자명을 기준으로 등록된 일정 목록을 전부 조회
    // 메서드 이름을 작성하면 JPA가 자동으로 생성해줌
    // 작성자명을 기준으로 전체 조회를 해줄 것이기 때문에 List로 만들어줌
    List<Calendar> findAllByName(String name);
}
