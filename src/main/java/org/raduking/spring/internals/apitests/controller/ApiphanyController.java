package org.raduking.spring.internals.apitests.controller;

import java.util.Objects;

import org.apiphany.ApiClient;
import org.raduking.spring.internals.apitests.client.ApiphanyClient;
import org.raduking.spring.internals.apitests.client.RestTemplateClient;
import org.raduking.spring.internals.apitests.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = { ApiClient.API })
public class ApiphanyController {

	private final ApiphanyClient apiphanyClient;

	private final RestTemplateClient restTemplateClient;

	public ApiphanyController(final ApiphanyClient apiphanyClient, final RestTemplateClient restTemplateClient) {
		this.apiphanyClient = apiphanyClient;
		this.restTemplateClient = restTemplateClient;
	}

	@GetMapping(path = "/customers/{customerId}")
	public Customer getCustomer(@PathVariable final String customerId) {
		Customer customer1 = apiphanyClient.getCustomer(customerId);
		Customer customer2 = restTemplateClient.getCustomer(customerId);

		return Objects.equals(customer1, customer2) ? customer1 : customer2;
	}

	@GetMapping(path = "/service/name")
	public String getServiceName() {
		String name1 = apiphanyClient.getServiceName();
		String name2 = restTemplateClient.getServiceName();

		return Objects.equals(name1, name2) ? name1 : name2;
	}

}
