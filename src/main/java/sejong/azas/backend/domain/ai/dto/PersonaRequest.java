package sejong.azas.backend.domain.ai.dto;

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
