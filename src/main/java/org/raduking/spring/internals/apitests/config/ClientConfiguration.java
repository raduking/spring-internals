package org.raduking.spring.internals.apitests.config;

import org.apiphany.client.ClientProperties;
import org.apiphany.client.ExchangeClient;
import org.apiphany.client.ExchangeClientBuilder;
import org.apiphany.client.http.ApacheHC5HttpExchangeClient;
import org.apiphany.client.http.JavaNetHttpExchangeClient;
import org.apiphany.client.http.RestTemplateExchangeClient;
import org.apiphany.spring.tracing.EnableTracingB3MultiHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@EnableTracingB3MultiHeaders
public class ClientConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientConfiguration.class);

	public static final String HTTP_EXCHANGE_CLIENT = "httpExchangeClient";
	public static final String APACHE_HC5_EXCHANGE_CLIENT = "apacheHC5ExchangeClient";
	public static final String REST_TEMPLATE_EXCHANGE_CLIENT = "restTemplateExchangeClient";

	public static final String HTTP_EXCHANGE_CLIENT_PROPERTIES = "httpEchangeClientProperties";

	private static final String MESSAGE_FAILED_TO_CREATE = "Failed to create {}";

	@Bean(HTTP_EXCHANGE_CLIENT_PROPERTIES)
	@ConfigurationProperties("clients")
	ClientProperties httpEchangeClientProperties() {
		return new ClientProperties();
	}

	@Bean(HTTP_EXCHANGE_CLIENT)
	ExchangeClient httpExchangeClient(@Qualifier(HTTP_EXCHANGE_CLIENT_PROPERTIES) final ClientProperties clientProperties) {
		try {
			return new JavaNetHttpExchangeClient(clientProperties);
		} catch (Exception e) {
			LOGGER.warn(MESSAGE_FAILED_TO_CREATE, HTTP_EXCHANGE_CLIENT, e);
			return none();
		}
	}

	@Bean(APACHE_HC5_EXCHANGE_CLIENT)
	ExchangeClient apacheHC5ExchangeClient(@Qualifier(HTTP_EXCHANGE_CLIENT_PROPERTIES) final ClientProperties clientProperties) {
		try {
			return new ApacheHC5HttpExchangeClient(clientProperties);
		} catch (Exception e) {
			LOGGER.warn(MESSAGE_FAILED_TO_CREATE, APACHE_HC5_EXCHANGE_CLIENT, e);
			return none();
		}
	}

	@Bean(REST_TEMPLATE_EXCHANGE_CLIENT)
	ExchangeClient restTemplateExchangeClient(@Qualifier(HTTP_EXCHANGE_CLIENT_PROPERTIES) final ClientProperties clientProperties) {
		try {
			return new RestTemplateExchangeClient(clientProperties);
		} catch (Exception e) {
			LOGGER.warn(MESSAGE_FAILED_TO_CREATE, REST_TEMPLATE_EXCHANGE_CLIENT, e);
			return none();
		}
	}

	ExchangeClient none() {
		return ExchangeClientBuilder.create().withDefaultClient().build().unwrap();
	}
}
