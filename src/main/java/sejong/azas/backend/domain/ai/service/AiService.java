package sejong.azas.backend.domain.ai.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.ai.dto.PersonaRequest;
import sejong.azas.backend.domain.ai.dto.PersonaResponse;

@Service
@RequiredArgsConstructor
public class AiService {
	private final RestClient restClient;

	public PersonaResponse getPersonaResult(PersonaRequest request) {
		return restClient.post()
			.uri("/api/ai/persona")
			.contentType(MediaType.APPLICATION_JSON)
			.body(request)
			.retrieve()
			.body(PersonaResponse.class);
	}
}
