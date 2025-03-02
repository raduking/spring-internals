package org.raduking.spring.internals.apitests.client;

import org.apiphany.ApiClient;
import org.apiphany.client.ExchangeClient;
import org.raduking.spring.internals.apitests.config.ClientConfiguration;
import org.raduking.spring.internals.apitests.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiphanyClient extends ApiClient {

	protected ApiphanyClient(@Value("${apiphany-tests.base-url}") final String baseUrl,
			@Qualifier(ClientConfiguration.HTTP_EXCHANGE_CLIENT) final ExchangeClient exchangeClient) {
		super(baseUrl, exchangeClient);
	}

	public Customer getCustomer(final String customerId) {
		return client()
				.get()
				.path(API, "customers", customerId)
				.retrieve(Customer.class)
				.orNull();
	}

	public String getServiceName() {
		return client()
				.get()
				.path(API, "service", "name")
				.retrieve(String.class)
				.orDefault("");
	}
}
