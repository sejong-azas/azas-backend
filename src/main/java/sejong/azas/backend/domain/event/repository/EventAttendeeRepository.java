package sejong.azas.backend.domain.event.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sejong.azas.backend.domain.event.entity.EventAttendee;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
    // 필드명이 event 이므로 ByEvent 뒤에 객체의 ID 필드인 Id를 붙입니다.
    List<EventAttendee> findAllByEvent_Id(Long eventId);
}