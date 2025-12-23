package sejong.azas.backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import sejong.azas.backend.domain.member.entity.Member;
import sejong.azas.backend.global.enums.Gender;

public record MemberRegisterRequest(
	@NotBlank
	String username,

	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	String password,

	Integer age,
	Long studentId,
	Gender gender,
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
	public static Member of(MemberRegisterRequest request){
		return Member.builder()
			.username(request.username)
			.password(request.password)
			.age(request.age)
			.studentId(request.studentId)
			.gender(request.gender)
			.chronotype(request.chronotype)
			.noiseSensitivity(request.noiseSensitivity)
			.cleaningCycle(request.cleaningCycle)
			.indoorActivity(request.indoorActivity)
			.thermalPreference(request.thermalPreference)
			.alarmHabit(request.alarmHabit)
			.itemSharing(request.itemSharing)
			.indoorEating(request.indoorEating)
			.isSmoker(request.isSmoker)
			.ageTolerance(request.ageTolerance)
			.build();
	}
}
