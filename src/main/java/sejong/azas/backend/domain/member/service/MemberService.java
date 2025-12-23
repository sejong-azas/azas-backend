package sejong.azas.backend.domain.member.service;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.auth.provider.AuthTokenProvider;
import sejong.azas.backend.domain.member.dto.MemberLoginRequest;
import sejong.azas.backend.domain.member.entity.Member;
import sejong.azas.backend.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final AuthTokenProvider authTokenProvider;

	@Transactional
	public void register(MemberLoginRequest request) {
		// 자체 로그인
		if (memberRepository.existsByUsername(request.username())) {
			throw new RuntimeException("이미 존재하는 회원입니다");
		}

		Member member = Member.builder()
			.username(request.username())
			.password(request.password())
			.build();

		memberRepository.save(member);
	}

	@Transactional
	public void login(MemberLoginRequest request, HttpServletResponse response) {
		if (!memberRepository.existsByUsername(request.username())) {
			throw new RuntimeException("회원가입이 필요합니다");
		}
		issueToken(response, request.username());
	}

	public void issueToken(HttpServletResponse response, String username) {
		// access token과 refresh token을 모두 발행

		String accessToken = authTokenProvider.createAccessToken(username);
		String refreshToken = authTokenProvider.createRefreshToken(username);

		ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(604800) // 7일
			.sameSite("None")
			.build();

		response.setHeader("Set-Cookie", refreshCookie.toString());
		response.setHeader("Authorization", "Bearer " + accessToken);
	}
}
