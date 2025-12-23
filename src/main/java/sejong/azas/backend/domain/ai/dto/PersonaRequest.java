package sejong.azas.backend.domain.ai.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PersonaRequest(
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
}
