package org.raduking.spring.internals.apitests.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

	public static final String REST_TEMPLATE_BEAN_NAME = "testRestTemplate";

	@Bean(REST_TEMPLATE_BEAN_NAME)
	RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

}
