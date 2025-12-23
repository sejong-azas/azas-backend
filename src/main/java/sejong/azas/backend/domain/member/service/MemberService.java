package sejong.azas.backend.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.auth.service.AuthService;
import sejong.azas.backend.domain.member.dto.MemberLoginRequest;
import sejong.azas.backend.domain.member.dto.MemberRegisterRequest;
import sejong.azas.backend.domain.member.entity.Member;
import sejong.azas.backend.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final AuthService authService;

	@Transactional
	public void register(MemberRegisterRequest request) {
		// 자체 로그인
		if (memberRepository.existsByUsername(request.username())) {
			throw new RuntimeException("이미 존재하는 회원입니다");
		}

		Member member = MemberRegisterRequest.of(request);
		memberRepository.save(member);
	}

	@Transactional
	public void login(MemberLoginRequest request, HttpServletResponse response) {
		if (!memberRepository.existsByUsername(request.username())) {
			throw new RuntimeException("회원가입이 필요합니다");
		}
		authService.issueToken(response, request.username());
	}
}
