package sejong.azas.backend.domain.ai.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AiPersonaRequest(
	Float chronotype,
	Float noiseSensitivity,
	Float cleaningCycle,
	Float indoorActivity,
	Float thermalPreference,
	Float alarmHabit,
	Float itemSharing,
	Float indoorEating,
	Float isSmoker,
	Float ageTolerance
) {
	public static AiPersonaRequest from(PersonaRequest request) {
		return new AiPersonaRequest(
			request.chronotype(),
			request.noiseSensitivity(),
			request.cleaningCycle(),
			request.indoorActivity(),
			request.thermalPreference(),
			request.alarmHabit(),
			request.itemSharing(),
			request.indoorEating(),
			request.isSmoker(),
			request.ageTolerance()
			);
	}
}
