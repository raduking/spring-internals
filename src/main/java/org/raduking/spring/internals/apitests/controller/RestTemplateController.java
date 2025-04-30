package org.raduking.spring.internals.apitests.controller;

import org.raduking.spring.internals.apitests.client.RestTemplateClient;
import org.raduking.spring.internals.apitests.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "rt" })
public class RestTemplateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateController.class);

	private final RestTemplateClient restTemplateClient;

	public RestTemplateController(final RestTemplateClient restTemplateClient) {
		this.restTemplateClient = restTemplateClient;
	}

	@GetMapping(path = "/customers/{customerId}")
	public Customer getCustomer(@PathVariable final String customerId) {
		Customer customer = restTemplateClient.getCustomer(customerId);
		LOGGER.info("Customer: {}", customer);
		return customer;
	}

	@GetMapping(path = "/service/name")
	public String getServiceName() {
		String name = restTemplateClient.getServiceName();
		LOGGER.info("Service Name: {}", name);
		return name;
	}

}
