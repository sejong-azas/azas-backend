package sejong.azas.backend.domain.event.dto;

import java.time.LocalDateTime;
import java.util.List;
import sejong.azas.backend.domain.event.entity.Event;

public record EventResponseDto(
    Long eventId,
    String title,
    String content,
    LocalDateTime startDateTime,
    LocalDateTime endDateTime,
    boolean isAllDay,
    String creatorName,
    List<String> attendeeNames
) {
    public static EventResponseDto from(Event event) {
        return new EventResponseDto(
            event.getId(),
            event.getTitle(),
            event.getContent(),
            event.getStartDateTime(),
            event.getEndDateTime(),
            event.isAllDay(),
            event.getCreator().getUsername(),
            event.getAttendees().stream()
                .map(a -> a.getMember().getUsername())
                .toList()
        );
    }
}