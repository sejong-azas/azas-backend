package sejong.azas.backend.domain.event.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import sejong.azas.backend.domain.event.dto.EventRequestDto;
import sejong.azas.backend.domain.member.entity.Member;
import sejong.azas.backend.domain.room.entity.Room;
import sejong.azas.backend.global.util.BaseTimeEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "event")
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private Member creator; // 이벤트 생성인 (1명)

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private boolean isAllDay;

    // 참여자 목록 조회를 위한 양방향 매핑
    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventAttendee> attendees = new ArrayList<>();

    public void addAttendee(Member member) {
        EventAttendee eventAttendee = EventAttendee.builder()
            .event(this)
            .member(member)
            .build();
        this.attendees.add(eventAttendee);
    }

    public void update(EventRequestDto dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.startDateTime = dto.startDateTime();
        this.endDateTime = dto.endDateTime();
        this.isAllDay = dto.isAllDay();
    }
}




