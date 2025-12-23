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
	String chronotypeInfo,
	Float noiseSensitivity,
	String noiseSensitivityInfo,
	Float cleaningCycle,
	String cleaningCycleInfo,
	Float indoorActivity,
	String indoorActivityInfo,
	Float thermalPreference,
	String thermalPreferenceInfo,
	Float alarmHabit,
	String alarmHabitInfo,
	Float itemSharing,
	String itemSharingInfo,
	Float indoorEating,
	String indoorEatingInfo,
	Float isSmoker,
	String isSmokerInfo,
	Float ageTolerance,
	String ageToleranceInfo
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
			.chronotypeInfo(request.chronotypeInfo)
			.noiseSensitivityInfo(request.noiseSensitivityInfo)
			.cleaningCycleInfo(request.cleaningCycleInfo)
			.indoorActivityInfo(request.indoorActivityInfo)
			.thermalPreferenceInfo(request.thermalPreferenceInfo)
			.alarmHabitInfo(request.alarmHabitInfo)
			.itemSharingInfo(request.itemSharingInfo)
			.indoorEatingInfo(request.indoorEatingInfo)
			.isSmokerInfo(request.isSmokerInfo)
			.ageToleranceInfo(request.ageToleranceInfo)
			.build();
	}
}
