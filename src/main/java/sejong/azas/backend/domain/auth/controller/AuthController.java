package sejong.azas.backend.domain.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	/*@Operation(summary = "토큰 재발급")
	@GetMapping("/refresh")
	public BaseResponse<?> refresh(HttpServletRequest request, HttpServletResponse response) {
		authService.reissueToken(request, response);

		return BaseResponse.success("토큰 재발급에 성공했습니다");
	}*/
}
