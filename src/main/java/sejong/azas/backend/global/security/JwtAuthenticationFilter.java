package sejong.azas.backend.global.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sejong.azas.backend.domain.auth.provider.AuthTokenProvider;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final AuthTokenProvider authTokenProvider;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain
	) throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String prefix = "Bearer ";

		if (StringUtils.hasText(header) && header.startsWith(prefix)) {
			String token = header.substring(prefix.length()).trim();

			if (authTokenProvider.isValidToken(token)) {
				String subject = authTokenProvider.getSubject(token);

				MemberPrincipal principal = new MemberPrincipal(subject, null);

				UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}
}