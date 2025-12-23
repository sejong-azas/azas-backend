package sejong.azas.backend.domain.auth.provider;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthTokenProvider {
	@Value("${custom.jwt.secret-key}")
	private String secret;

	@Value("${custom.jwt.access-expiration}")
	private int jwtAccessExpiration;

	@Value("${custom.jwt.refresh_expiration}")
	private int jwtRefreshExpiration;

	private SecretKey secretKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	private String buildToken(String subject, long expireSeconds) {
		Date issuedAt = new Date();
		Date expiration = new Date(issuedAt.getTime() + 1000L * expireSeconds);

		return Jwts.builder()
			.subject(subject)
			.issuedAt(issuedAt)
			.expiration(expiration)
			.signWith(secretKey())
			.compact();
	}

	public String createAccessToken(String username) {
		return buildToken(username, jwtAccessExpiration);
	}

	public String createRefreshToken(String username) {
		return buildToken(username, jwtRefreshExpiration);
	}

}
