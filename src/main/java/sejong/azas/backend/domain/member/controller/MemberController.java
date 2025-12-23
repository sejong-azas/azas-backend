package sejong.azas.backend.domain.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.member.dto.MemberLoginRequest;
import sejong.azas.backend.domain.member.dto.MemberRegisterRequest;
import sejong.azas.backend.domain.member.service.MemberService;
import sejong.azas.backend.global.util.BaseResponse;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@Operation(summary = "회원가입")
	@PostMapping("/register")
	public BaseResponse<?> register(@Valid @RequestBody MemberRegisterRequest request) {
		memberService.register(request);
		return BaseResponse.success("회원가입에 성공했습니다");
	}

	@Operation(summary = "로그인")
	@PostMapping("/login")
	public BaseResponse<?> login(@Valid @RequestBody MemberLoginRequest request, HttpServletResponse response) {
		memberService.login(request, response);
		return BaseResponse.success("로그인에 성공했습니다");
	}
}
