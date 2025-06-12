package org.raduking.spring.internals.apitests.config;

import org.apiphany.client.ClientProperties;
import org.apiphany.client.ExchangeClient;
import org.apiphany.client.http.ApacheHC5ExchangeClient;
import org.apiphany.client.http.JavaNetHttpExchangeClient;
import org.apiphany.spring.client.RestTemplateExchangeClient;
import org.apiphany.spring.tracing.EnableTracingB3MultiHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@EnableTracingB3MultiHeaders
public class ClientConfiguration {

	public static final String HTTP_EXCHANGE_CLIENT = "httpExchangeClient";
	public static final String APACHE_HC5_EXCHANGE_CLIENT = "apacheHC5ExchangeClient";
	public static final String REST_TEMPLATE_EXCHANGE_CLIENT = "restTemplateExchangeClient";

	public static final String HTTP_EXCHANGE_CLIENT_PROPERTIES = "httpEchangeClientProperties";

	@Bean(HTTP_EXCHANGE_CLIENT_PROPERTIES)
	@ConfigurationProperties("clients")
	ClientProperties httpEchangeClientProperties() {
		return new ClientProperties();
	}

	@Bean(HTTP_EXCHANGE_CLIENT)
	ExchangeClient httpExchangeClient(@Qualifier(HTTP_EXCHANGE_CLIENT_PROPERTIES) final ClientProperties clientProperties) {
		return new JavaNetHttpExchangeClient(clientProperties);
	}

	@Bean(APACHE_HC5_EXCHANGE_CLIENT)
	ExchangeClient apacheHC5ExchangeClient(@Qualifier(HTTP_EXCHANGE_CLIENT_PROPERTIES) final ClientProperties clientProperties) {
		return new ApacheHC5ExchangeClient(clientProperties);
	}

	@Bean(REST_TEMPLATE_EXCHANGE_CLIENT)
	ExchangeClient restTemplateExchangeClient(@Qualifier(HTTP_EXCHANGE_CLIENT_PROPERTIES) final ClientProperties clientProperties) {
		return new RestTemplateExchangeClient(clientProperties);
	}

}
