package org.raduking.spring.internals.perf.service;

import org.apiphany.ApiClient;
import org.apiphany.ApiRequest;
import org.apiphany.client.ExchangeClient;
import org.raduking.spring.internals.apitests.config.ClientConfiguration;
import org.raduking.spring.internals.perf.AbstractPerformanceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApiPerformance extends AbstractPerformanceService<ApiPerformance.Settings> {

	private final ApiPerformanceClient apiClient;

	public ApiPerformance(@Qualifier(ClientConfiguration.HTTP_EXCHANGE_CLIENT) final ExchangeClient exchangeClient) {
		this.apiClient = new ApiPerformanceClient(exchangeClient);
	}

	@Override
	public void run(final Settings customSettings) {
		apiClient.exchange(customSettings);
	}

	@Override
	public Class<Settings> getCustomSettingsClass() {
		return Settings.class;
	}

	public static class ApiPerformanceClient extends ApiClient {

		protected ApiPerformanceClient(final ExchangeClient exchangeClient) {
			super(exchangeClient);
		}

		public String exchange(final Settings settings) {
			return client()
					.method(settings.getMethod())
					.url(settings.getUrl())
					.body(settings.getBody())
					.retrieve(String.class)
					.orRethrow();
		}

	}

	public static class Settings extends ApiRequest<Object> {
		// empty
	}

}
