package sejong.azas.backend.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.azas.backend.global.enums.Gender;
import sejong.azas.backend.global.util.BaseTimeEntity;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "age")
	private Integer age;

	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "chronotype")
	private Float chronotype;

	@Column(name = "noise_sensitivity")
	private Float noiseSensitivity;

	@Column(name = "cleaning_cycle")
	private Float cleaningCycle;

	@Column(name = "indoor_activity")
	private Float indoorActivity;

	@Column(name = "thermal_preference")
	private Float thermalPreference;

	@Column(name = "alarm_habit")
	private Float alarmHabit;

	@Column(name = "item_sharing")
	private Float itemSharing;

	@Column(name = "indoor_eating")
	private Float indoorEating;

	@Column(name = "is_smoker")
	private Float isSmoker;

	@Column(name = "age_tolerance")
	private Float ageTolerance;
}
