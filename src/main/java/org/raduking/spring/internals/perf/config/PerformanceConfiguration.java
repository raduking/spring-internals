package org.raduking.spring.internals.perf.config;

import org.apiphany.client.ExchangeClient;
import org.raduking.spring.internals.apitests.config.ClientConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.stressium.EnableStressium;
import org.stressium.service.ApiPerformance;

@Configuration
@EnableStressium
public class PerformanceConfiguration {

	@Bean
	ApiPerformance apiPerformance(@Qualifier(ClientConfiguration.HTTP_EXCHANGE_CLIENT) ExchangeClient exchangeClient) {
		return new ApiPerformance(exchangeClient);
	}
}
