package sejong.azas.backend.domain.auth.service;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.auth.provider.AuthTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

	public final AuthTokenProvider authTokenProvider;

	@Transactional
	public void reissueToken(HttpServletRequest request, HttpServletResponse response){
		// 블랙리스트 등록
		//String refreshToken = blackRefreshToken(request);
		String refreshToken = authTokenProvider.getRefreshToken(request)
			.orElseThrow(() -> new RuntimeException("유효하지 않은 토큰입니다"));

		// 토큰 재발급
		String username = authTokenProvider.getSubject(refreshToken);
		issueToken(response, username);
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
