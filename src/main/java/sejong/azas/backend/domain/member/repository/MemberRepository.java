package sejong.azas.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sejong.azas.backend.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	boolean existsByUsername(String username);
}
