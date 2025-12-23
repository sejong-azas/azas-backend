package sejong.azas.backend.domain.event.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.azas.backend.domain.event.dto.EventRequestDto;
import sejong.azas.backend.domain.event.dto.EventResponseDto;
import sejong.azas.backend.domain.event.entity.Event;
import sejong.azas.backend.domain.event.repository.EventRepository;
import sejong.azas.backend.domain.member.entity.Member;
import sejong.azas.backend.domain.member.repository.MemberRepository;
import sejong.azas.backend.domain.room.entity.Room;
import sejong.azas.backend.domain.room.repository.RoomRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public Long createEvent(Long creatorId, EventRequestDto dto) {
        Member creator = memberRepository.findById(creatorId)
            .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다."));
        Room room = roomRepository.findById(dto.roomId())
            .orElseThrow(() -> new IllegalArgumentException("해당 방을 찾을 수 없습니다."));

        Event event = Event.builder()
            .title(dto.title())
            .content(dto.content())
            .startDateTime(dto.startDateTime())
            .endDateTime(dto.endDateTime())
            .isAllDay(dto.isAllDay())
            .creator(creator)
            .room(room)
            .build();

        // 참여자 추가 (null 체크 추가)
        if (dto.attendeeIds() != null) {
            dto.attendeeIds().forEach(id -> {
                Member member = memberRepository.findById(id).orElseThrow();
                event.addAttendee(member);
            });
        }

        return eventRepository.save(event).getId();
    }

    public List<EventResponseDto> getRoomEvents(Long roomId) {
        // Room 존재 여부 체크 후 조회 권장
        return eventRepository.findAllByRoomId(roomId)
            .stream()
            .map(EventResponseDto::from)
            .toList();
    }

    @Transactional
    public void updateEvent(Long eventId, EventRequestDto dto) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("해당 이벤트를 찾을 수 없습니다."));

        // 1. 기본 정보 수정 (Entity에 구현한 update 메서드 호출)
        event.update(dto);

        // 2. 참여자 수정 (기존 목록 비우기)
        // orphanRemoval = true 설정 덕분에 리스트를 비우면 DB에서도 삭제됩니다.
        event.getAttendees().clear();

        // 3. 새로운 참여자 추가
        if (dto.attendeeIds() != null) {
            dto.attendeeIds().forEach(id -> {
                Member member = memberRepository.findById(id).orElseThrow();
                event.addAttendee(member);
            });
        }
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new IllegalArgumentException("삭제하려는 이벤트가 존재하지 않습니다.");
        }
        eventRepository.deleteById(eventId);
    }
}