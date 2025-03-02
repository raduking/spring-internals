package org.raduking.spring.internals.apitests.client;

import org.raduking.spring.internals.apitests.config.RestConfiguration;
import org.raduking.spring.internals.apitests.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateClient {

	private final String baseUrl;
	private final RestTemplate restTemplate;

	protected RestTemplateClient(@Value("${apiphany-tests.base-url}") final String baseUrl,
			@Qualifier(RestConfiguration.REST_TEMPLATE_BEAN_NAME) final RestTemplate restTemplate) {
		this.baseUrl = baseUrl;
		this.restTemplate = restTemplate;
	}

	public Customer getCustomer(final String customerId) {
		return restTemplate.getForObject(baseUrl + "/api/customers/" + customerId, Customer.class);
	}

	public String getServiceName() {
		return restTemplate.getForObject(baseUrl + "/api/service/name", String.class);
	}
}
