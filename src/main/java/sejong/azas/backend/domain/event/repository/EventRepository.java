package sejong.azas.backend.domain.event.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sejong.azas.backend.domain.event.entity.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
    // 특정 방의 모든 이벤트 조회
    List<Event> findAllByRoomId(Long roomId);
}