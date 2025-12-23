package sejong.azas.backend.domain.event.controller;

import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.azas.backend.domain.event.dto.EventRequestDto;
import sejong.azas.backend.domain.event.dto.EventResponseDto;
import sejong.azas.backend.domain.event.service.EventService;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody EventRequestDto dto) {
        // 실제로는 인증 정보에서 creatorId를 가져와야 함 (현재는 임시 1L)
        Long creatorId = 1L;
        return ResponseEntity.ok(eventService.createEvent(creatorId, dto));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<EventResponseDto>> getEvents(@PathVariable Long roomId) {
        return ResponseEntity.ok(eventService.getRoomEvents(roomId));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Void> update(@PathVariable Long eventId, @RequestBody EventRequestDto dto) {
        eventService.updateEvent(eventId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> delete(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}