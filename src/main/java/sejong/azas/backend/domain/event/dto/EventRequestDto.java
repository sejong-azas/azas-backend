package sejong.azas.backend.domain.event.dto;

import java.time.LocalDateTime;
import java.util.List;

public record EventRequestDto(
    String title,
    String content,
    LocalDateTime startDateTime,
    LocalDateTime endDateTime,
    boolean isAllDay,
    Long roomId,
    List<Long> attendeeIds // 참여자들의 Member ID 리스트
) {}