package org.raduking.spring.internals.apitests.controller;

import org.apiphany.ApiClient;
import org.raduking.spring.internals.apitests.client.ApiphanyClient;
import org.raduking.spring.internals.apitests.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { ApiClient.API })
public class ApiphanyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiphanyController.class);

	private final ApiphanyClient apiphanyClient;

	public ApiphanyController(final ApiphanyClient apiphanyClient) {
		this.apiphanyClient = apiphanyClient;
	}

	@GetMapping(path = "/customers/{customerId}")
	public Customer getCustomer(@PathVariable final String customerId) {
		Customer customer = apiphanyClient.getCustomer(customerId);
		LOGGER.info("Customer: {}", customer);
		return customer;
	}

	@GetMapping(path = "/service/name")
	public String getServiceName() {
		String name = apiphanyClient.getServiceName();
		LOGGER.info("Service Name: {}", name);
		return name;
	}

}
