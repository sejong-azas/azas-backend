package sejong.azas.backend.global.util;

import org.springframework.http.HttpStatus;

public record BaseResponse<T>(
	HttpStatus status,
	String message,
	T detail
) {
	public static BaseResponse<?> success(String message) {
		return new BaseResponse<>(HttpStatus.OK, message, null);
	}

	public static <T> BaseResponse<T> success(String message, T data) {
		return new BaseResponse<>(HttpStatus.OK, message, data);
	}
}
