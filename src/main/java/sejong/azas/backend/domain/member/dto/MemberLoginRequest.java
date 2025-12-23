package sejong.azas.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
	@NotBlank
	String username,

	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	String password
) {
}
