package sejong.azas.backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AiConfig {
	@Value("${custom.ai-server}")
	private String server;
	@Bean
	public RestClient aiRestClient() {
		return RestClient.builder()
			.baseUrl("http://" + server + ":8000")
			.build();
	}
}
