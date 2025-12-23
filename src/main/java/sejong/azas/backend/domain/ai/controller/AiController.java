package sejong.azas.backend.domain.ai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.ai.dto.PersonaRequest;
import sejong.azas.backend.domain.ai.dto.PersonaResponse;
import sejong.azas.backend.domain.ai.service.AiService;
import sejong.azas.backend.global.util.BaseResponse;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

	private final AiService aiService;

	@PostMapping("/persona")
	public BaseResponse<PersonaResponse> getPersona(@RequestBody PersonaRequest request) {
		PersonaResponse response = aiService.getPersonaResult(request);
		return BaseResponse.success("유형 분석이 완료되었습니다", response);
	}
}
