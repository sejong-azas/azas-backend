package sejong.azas.backend.domain.room.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sejong.azas.backend.domain.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
