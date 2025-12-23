package sejong.azas.backend.domain.ai.dto;

import java.util.List;

public record PersonaResponse(
	String title,
	List<String> tags
) {
}
